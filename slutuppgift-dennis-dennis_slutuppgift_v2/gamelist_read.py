import json
from gamelist_write import GameWriter
from operator import itemgetter


text = GameWriter()


class GameListHandler:
    
    def to_print_all_games(self):
        game_list = self.to_load_gamelist()
        game_list = sorted(game_list["games"],key=itemgetter("rating"),reverse=True)
        print("---------- GAME LIST ----------")
        
        for game in game_list:
            print(f"Game:",game['gamename'],"\nAmount of players:",game['players'], 
            "\nGame Duration:",game['duration'],"\nAge Limit:",game['age_limit'],"\nRating:",game['rating'],
            "\n--------------------------------")     
        json_pprint = json.dumps(game['gamename'])
        return json_pprint

    def get_if_game_already_exists(self,searched_game):
        game_list = self.to_load_gamelist()
        for game in game_list['games']:
            if game['gamename'] == searched_game:
                return True
        return False

    
    def append_newlycreated_game(self, new_game):  
        game_list = self.to_load_gamelist()
        game_list['games'].append(new_game)
        self.to_commit_changes_json(game_list)

    
    def to_commit_changes_json(self,data):
        with open('Gamelist.json', 'w') as data_file:
            data = json.dump(data, data_file,indent=4)
    
    def to_load_gamelist(self):
        with open('Gamelist.json') as f:
            data = json.load(f)   
            return data
        

    def apply_users_filters(self):
        game_list = self.to_load_gamelist()
        try:
            game_filter = input("Apply a filter by typing players,duration,age_limit, or write 'return' for main menu: ")
            if game_filter == "return":
                    return
            
            user_requirements = int(input("Input a value: "))
            
            if game_filter == 'age_limit':
                
                another_game_filter = input("Want another Filter? (Y/N) ")
                if another_game_filter == "Y":   
                    
                    game_filter2 = input("Apply another filter:")
                    user_requirements2 = int(input("Input a value: "))
                    if game_filter2 == 'players':
                        for age_players in game_list['games']:
                            if age_players['age_limit'] == user_requirements and age_players['players'] == user_requirements2:
                                print(age_players)
                                
                    if game_filter2 == 'duration':
                            if age_players['age_limit'] == user_requirements and age_players['duration'] == user_requirements2:
                                print(age_players)


                elif another_game_filter == "N":
                    for age in game_list['games']:
                        if age['age_limit'] == user_requirements:
                            print(age)
                
                else:
                    print("Please enter Y or N")
                    return

            if game_filter == 'duration':
                another_game_filter = input("Want another Filter? (Y/N) ")
                if another_game_filter == "Y":   
                    game_filter2 = input("Apply another filter:")
                    user_requirements2 = int(input("Input a value: "))
                    if game_filter2 == 'players':
                        for duration_players in game_list['games']:
                            if duration_players['duration'] == user_requirements and duration_players['players'] == user_requirements2:
                                print(duration_players)
                
                    if game_filter2 == 'age_limit':
                            for duration_age in game_list['games']:
                                if duration_age['duration'] == user_requirements and duration_age['age_limit'] == user_requirements2:
                                    print(duration_age)
                    
                elif another_game_filter == "N":
                    for d in game_list['games']:
                        if d['duration'] == user_requirements:
                            print(d)
                    
            
                else: 
                    print("Please enter Y or N")
                    return


            if game_filter == 'players':
                another_game_filter = input("Want another Filter? (Y/N) ")
                if another_game_filter == "Y":   
                    game_filter2 = input("Apply another filter:")
                    user_requirements2 = int(input("Input a value: "))
                    if game_filter2 == 'duration':
                        for duration_players in game_list['games']:
                            if duration_players['players'] == user_requirements and duration_players['duration'] == user_requirements2:
                                print(duration_players)

                    if game_filter2 == 'age_limit':
                            for players_age in game_list['games']:
                                if players_age['players'] == user_requirements and players_age['age_limit'] == user_requirements2:
                                    print(players_age)
                        

                elif another_game_filter == "N":
                    for players in game_list['games']:
                        if players['players'] == user_requirements:
                            print(players)
                    
            
                else: 
                    print("Please enter Y or N")
                    return

        except KeyError:
            print("Invalid Key")

        except ValueError:
            print("Please input a int")

    
handler = GameListHandler()    

class GameListEditor():

    def to_create_newgame(self):
        new_game = text.prompt_new_game()
        if not handler.get_if_game_already_exists(new_game['gamename']):
            handler.append_newlycreated_game(new_game)
            print("Game added!")
        else:
            print("THIS GAME ALREADY EXISTS.")

    def set_new_gamename(self):
        game_list = handler.to_load_gamelist()
        new_gamename = text.game_editor()
        duplicate_tester = handler.get_if_game_already_exists(new_gamename[1])
        if duplicate_tester == True:
            print("Game name already exist")
            pass

        else:
            for game in game_list['games']:
                if new_gamename[0] in game['gamename']:
                    game['gamename'] = new_gamename[1]
                    game['players'] = new_gamename[2]
                    game['duration'] = new_gamename[3]
                    game['age_limit'] = new_gamename[4]
                    print("Game changed")
        
            handler.to_commit_changes_json(game_list)

    
    def set_game_rating(self):
        game_list = handler.to_load_gamelist()
        wich_game = input("Wich game you want to rate: ")
        for game in game_list['games']:
            if wich_game == game['gamename']:
                new_rating = text.game_rating()
                game['rating'] = new_rating
                handler.to_commit_changes_json(game_list)

    def to_remove_game(self):
        game_list = handler.to_load_gamelist()
        to_remove = input("Which game do you want to remove? ").capitalize()
        for index, value in enumerate(game_list['games']):
            if to_remove == value['gamename']:
                del game_list['games'][index]
                print("----- DELETED -----")
                handler.to_commit_changes_json(game_list)
                return True
        print("Game doesnt exist.")
        return False