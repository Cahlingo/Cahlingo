from cmd import Cmd
from gamelist_write import GameWriter
from gamelist_read import GameListEditor, GameListHandler

edit_functions = GameListEditor()
file_functions = GameListHandler()
text_functions = GameWriter()

class MyCmd(Cmd):
    intro = "[1] to list all games \
    \n[2] create a new game     \
    \n[3] Removes a game from list \
    \n[4] Edit a game from the list \
    \n[5] Filtering the games for you\
    \n[6] Rate a game from the list \
    \n[q] 'quit' to exit the program.\
    \n[o] Press 'o' to show options."
    prompt = "Which option do you choose? "
    
    def do_1(self,arg):
        try:
            file_functions.to_print_all_games()
            pass
        except UnboundLocalError:
            print("NO GAMES HERE YET, ADD EM PEASANT!")

    def do_2(self,arg):
        edit_functions.to_create_newgame()
    
    def do_3(self,arg):
        edit_functions.to_remove_game()

    def do_4(self,arg):
        edit_functions.set_new_gamename()

    def do_5(self,arg):
        file_functions.apply_users_filters()

    def do_6(self,arg):
        edit_functions.set_game_rating()

    def do_quit(self, arg):
        print("---- Ceasing functions. SEE YAH. ----")
        exit()     
    
    def do_o(self,arg):
        MyCmd().cmdloop()

if __name__ == '__main__':  
    MyCmd().cmdloop()
