from server_command_handler import ServerCommandHandler


class TestServerCommandHandler():

    def test_handle_client_input_correctcommand(self):
        server_command = ServerCommandHandler()
        assert server_command.handle_client_input("/LIST") == "LIST"

    def test_handle_client_input_correctcommand_PIC(self):
        server_command = ServerCommandHandler()
        assert server_command.handle_client_input("/PIC") == "PICTURE"

    def test_handle_client_input_space_between_command(self):
        server_command = ServerCommandHandler()
        assert server_command.handle_client_input("/ LIST") == "Invalid command"

    def test_handle_client_input_space_between_command_PIC(self):
        server_command = ServerCommandHandler()
        assert server_command.handle_client_input("/ PIC") == "Invalid command"

    def test_handle_client_input_CORRECTCOMMAND_HELP(self):
        server_command = ServerCommandHandler()
        assert server_command.handle_client_input("/HELP") == "HELP"

    def test_handle_client_input_split(self):
        server_command = ServerCommandHandler()
        assert server_command.handle_client_input("/w Hej") == "Hej"

    def test_hhandle_client_input_split(self):
        server_command = ServerCommandHandler()
        assert server_command.handle_client_input("/w") == "Invalid command"

    def test_handle_client_input_IF_NO_COMMAND(self):
        server_command = ServerCommandHandler()
        assert server_command.handle_client_input("Hej") == "Hej"

    def test_get_whisper_correct(self):
        server_command = ServerCommandHandler()
        assert server_command.get_whisper("rompa hej") == ('rompa', 'hej')

    def test_get_whisper_wronginput(self):
        server_command = ServerCommandHandler()
        assert server_command.get_whisper(
            "rompa hej hej") == ('rompa', 'hej hej')

    def test__handle_comand_correctcommands(self):
        server_command = ServerCommandHandler()
        assert server_command._handle_command("w", "Tjena") == "Tjena"

    def test__handle_comand_wrong_command(self):
        server_command = ServerCommandHandler()
        assert server_command._handle_command(
            "blabla", "gustav") == "Invalid command"
