
from io import BytesIO
import socket
import threading
from PIL import Image

HOST = 'localhost'
PORT = 5050


class Client:

    def __init__(self):
        self.username = input("Input your username: ").title()
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client.connect((HOST, PORT))
        receive_thread = threading.Thread(target=self.receive)
        receive_thread.start()

    def write(self):
        while True:
            message = input("")
            self.client.send(message.encode())
            if not message:
                continue

    def receive(self):
        while True:
            BUFF_SIZE = 1024  # 4 KiB
            datalist = []
            while True:
                part = self.client.recv(BUFF_SIZE)
                datalist.append(part)
                if len(part) < BUFF_SIZE:
                    break
            data = b''.join(datalist)
            try:
                msg = data.decode()
                if msg == 'USER':
                    self.client.send(self.username.encode())
                elif msg == "quit":
                    break
                elif msg == "PICTURE":
                    self.send_file()
                else:
                    print(msg)
            except UnicodeDecodeError:
                while True:
                    p = BytesIO(data)
                    img = Image.open(p)
                    img.show()
                    break

    def send_file(self):
        try:
            specific_jpg = input("INPUT FILENAME: ")
            with open(f"{specific_jpg}", "rb") as f:
                f = f.read()
                print("Sending...")
                self.client.sendall(f)
        except FileNotFoundError:
            no_file = "FileNotFound"
            self.client.sendall(no_file.encode())


if __name__ == "__main__":
    client = Client()
    client.write()
