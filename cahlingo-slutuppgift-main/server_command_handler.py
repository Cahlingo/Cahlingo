
class ServerCommandHandler:

    def handle_client_input(self, data: str):

        command = data[0]
        if command == '/':
            if data[1:].upper() == "LIST":
                return "LIST"
            elif data[1:].upper() == "PIC":
                return "PICTURE"
            elif data[1:].upper() == "HELP":
                return "HELP"
            try:
                command, rest = data[1:].split(" ", 1)
                result = self._handle_command(command, rest)
            except ValueError:
                data = "Invalid command"
                return data
            return result
        return data

    def get_whisper(self, rest: str):
        # TODO ERROR HANDLING IF NO MSG.
        while True:
            try:
                user, msg = rest.split(" ", 1)
                return user, msg
            except ValueError:
                return rest

    def _handle_command(self, command: str, rest: str):
        rest = rest.title()
        if command == 'w' or command == 'W':
            result = self.get_whisper(rest)
            return result
        return "Invalid command"
