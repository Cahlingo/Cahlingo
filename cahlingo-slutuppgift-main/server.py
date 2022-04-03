import socket
import threading
from server_command_handler import ServerCommandHandler


class Server:
    HOST = 'localhost'
    PORT = 5050
    ADDR = HOST, PORT

    def __init__(self):
        self.s = socket.socket()
        self.s.bind(Server.ADDR)
        self.s.listen()
        self.handle_inputs = ServerCommandHandler()
        self.user = {}

    def handle_new_connections(self):
        print(f"Server is listening on {Server.ADDR}")
        while True:
            conn, addr = self.s.accept()
            conn.send('USER'.encode())
            username = conn.recv(1024).decode()
            self.user[username] = conn
            thread = threading.Thread(
                target=self.handle, args=(conn, username))
            thread.start()
            self.chat_announcement(conn, f"{username} has joined the chat")
            conn.sendall("Write /help for chat commands".encode())

    # HANDLE ALL INPUTS FROM THE CLIENT USING THE SERVER_HANDLER
    def handle(self, conn: socket.socket, username):
        while True:
            try:
                BUFF_SIZE = 1024  # 4 KiB
                datalist = []
                while True:
                    part = conn.recv(BUFF_SIZE)
                    datalist.append(part)
                    if len(part) < BUFF_SIZE:
                        break
                data = b''.join(datalist)
                msg = data.decode()
                reply = self.handle_inputs.handle_client_input(msg)
                self.handle_command(conn, reply, username, msg)
            except ConnectionResetError:
                self._close_connection(conn, username)
                break
            except UnicodeDecodeError:
                self.broadcast_image(conn, username, data)

    def handle_command(self, conn: socket.socket, reply, username, msg):

        if reply == "LIST":
            self._send_active_users(conn)
        elif reply == "PICTURE":
            conn.sendall("PICTURE".encode())
        elif reply == "FileNotFound":
            conn.sendall("File Not Found".encode())
        elif reply == "HELP":
            conn.sendall("/List for users in chat, /pic to send pic, '/w username msg' to send private msg".encode())
        elif reply == "Invalid command":
            error_msg = "Invalid command, write /help for command list!"
            conn.sendall(error_msg.encode())

        elif type(reply) == tuple:
            user_to_w, msg = reply
            self.whisper_client(conn, user_to_w, username, msg)
        else:
            self.broadcast_msg(conn, f"{username} said: {msg}", msg)

    # FUNCTION THAT SENDS A MSG TO SPECIFIC USER

    def whisper_client(self, conn: socket.socket, user_to_w, from_user, msg):
        found_user = False
        for user, connections in self.user.items():
            if user == user_to_w:
                found_user = True
                connections.sendall(f"{from_user} whispered: {msg} ".encode())
        if not found_user:
            no_match = "User not in list"
            conn.sendall(no_match.encode())

    # FUNCTION THAT BROADCASTS EVERY MSG THAT ISNT A COMMAND OR A WHISPER.
    def broadcast_msg(self, conn: socket.socket, username: str, msg: str):
        for user, conn in self.user.items():
            conn.sendall(username.encode())

    def broadcast_image(self, conn: socket.socket, username: str, jpg: bytes):
        for user, conn in self.user.items():
            conn.sendall(jpg)

    def chat_announcement(self, conn: socket.socket, username):
        for user, conn in self.user.items():
            conn.sendall(username.encode())

    # RETURNS A STR WITH ALL ACTIVE USERNAMES THAT ARE CONNECTED TO THE SERVER/CHAT
    def _send_active_users(self, conn: socket.socket):
        user_str = ", "
        user_str = user_str.join(self.user)
        conn.send(f"Users in chat: {user_str}".encode())

    # CLOSES THE CONNECTION WITH THE USERS SOCKET AND RETURNS IT.
    def _close_connection(self, conn: socket.socket, username):
        del self.user[username]
        self.chat_announcement(conn, f"{username} has left the chat")
        conn.close()

    def loop(self):
        while True:
            self.handle_new_connections()


if __name__ == "__main__":
    server = Server()
    server.loop()
