

class GameWriter:
      
    def prompt_new_game(self):

            print("----- Game Creator -----")
            game_name = input("Insert Gamename: ").capitalize()
            print("----- ONLY INPUT NUMBERS BELOW HERE -----")
            while True:
                try:
                    players = int(input("Insert amount of players:"))
                    break
                except ValueError:
                    print("ONLY NUMBERS ALLOWED!!")
                    continue
            while True:
                    try:
                        duration = int(input("Game lenght in minutes:"))
                        break
                    except ValueError:
                        print("ONLY NUMBERS ALLOWED!!")
                        continue
            while True:        
                try:
                    age_limit = int(input("Insert age limit:"))
                    break
                except ValueError:
                    print("ONLY NUMBERS ALLOWED!!")
                    continue 
            while True:
                rating = 0
                break



                
            new_game = {
            "gamename":game_name,"players":players,
            "duration":duration,"age_limit":age_limit,
            "rating":rating
            
                        }
            return new_game

    def game_editor(self):
            old_gamename = input("Input the old game: ")
            newgame_name = input("Input new game name: ")
            print("----- ONLY INPUT NUMBERS BELOW HERE -----")
            while True:
                try:
                    new_gamename_player = int(input("Input new player count: "))
                    break
                except ValueError:
                    print("ONLY NUMBERS ALLOWED!!")
                    continue                
            while True:
                try:
                    new_gamename_lenght = int(input("Input new gamelenght: "))
                    break
                except ValueError:
                    print("ONLY NUMBERS ALLOWED!!")
                    continue 
            while True:
                try:   
                    new_gamename_age_limit = int(input("Input new age limit: "))
                    break
                except ValueError:
                    print("ONLY NUMBERS ALLOWED!!")
                    continue
            return old_gamename,newgame_name,new_gamename_player,new_gamename_lenght,new_gamename_age_limit

    def game_rating(self):
        print("----- GAME RATER -----")
        while True:
            try:

                ratings = int(input("RATE YOUR GAME OF CHOICE!: "))
                break
            except ValueError:
                print("ONLY NUMBERS ALLOWED!!")
                continue
        
        return ratings

    def gamefilter_text(self):
        print("----- INPUT DESIRED FILTERS -----")
        print("[P]: min amount of players\
        \n[D]: Max game duration\
        \n[A]: Age recommendation")
