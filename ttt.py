# while not ended, play next play next move
#     check if ended
#         if not end, display current play chart
#         if ended, display winer,
#             ask the user if will end the game



playing = True
next_player = "X"
board = [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']]
print(board)


#     check if ended
def check_winner():
    # check for winner, return if there is one and who is it
    is_winner = False
    winner = "Nobody"
    for n in range(3):
        # check for rows
        if board[n][0] == board[n][1] and board[n][1] == board[n][2]:
            is_winner = True
            winner = board[r][0]
        # check for colums
        elif board[0][n] == board[1][n] and board[1][n] == board[2][n]:
            is_winner = True
            winner = board[0][n]
    # check for left_diagonal
    if (board[0][0] == board[1][1] and board[1][1] == board[2][2]):
        is_winner = True
        winner = board[0][0]
        # check for right_diagonal
    elif (board[0][2] == board[1][1] and board[1][1] == board[2][0]):
        is_winner = True
        winner = board[0][2]
    return (is_winner, winner)


def print_board():
    for i in range(3):
        print(" ".join(board[i]))
    return


def place_board():
    global next_player
    global board
    # place user's choice in board
    result = False
    next_move = input("select next move for " + next_player)
    for r in range(3):
        for c in range(3):
            if next_move == board[r][c]:

                board[r][c] = next_player
                if next_player == "X":
                    next_player = "O"
                else:
                    next_player = "X"

                result = True
    return result


# while not ended, play next play next move
def main():
    global playing
    global board
    global next_player
    while playing:
        #         if not end, display current play chart
        print_board()
        next_choice_ok = False
        while not next_choice_ok:
            next_choice_ok = place_board()
            if next_choice_ok == "n/a":
                break
            not_game_over = False
            for r in range(3):
                for c in range(3):
                    not_game_over = not_game_over or board[r][c].isdigit()

            if not not_game_over:
                break

        (is_winner, winner) = check_winner()
        #         if ended, display winer,
        if is_winner or not not_game_over:
            print_board()
            keep_going = input(winner + " Win. press 'y' if you want to play again")
            #             ask the user if will end the game
            if keep_going in ("y", "Y"):
                next_player = "X"
                board = [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']]
            else:
                playing = False


if __name__ == "__main__" :
    main()


