# author : https://github.com/njavig
# in progress

# IPO chart
# input: a file
# processing: chop line by line
#             Style Rule 1: Put a space after an open parenthesis "(" and
#                           one before a close parenthesis ")".
#                           -> check if there is space between ( and )
#             Style Rule 2: Put a space before and after each operator.
#                           -> check if an operator is between spaces
# output: if there is (an) error(s), tell user where it was, what it was.
#         if there is no error, end the program and inform about other rules

# Pseudocode

# Function 2 - Style Rule 2
# GET a row (string) and the row index
# IF 2-bit operators ( ** // += -= *= /= >= <= == != ) in the string, THEN
#     GET its index
#     IF NOT index - 1 and index + 2 is both " ", THEN
#
# ELSEIF 1-bit operators ( + - * / = < > % ) in the string, THEN
#     GET its index
#     IF NOT index - 1 and index + 1 is both " ", THEN
#


# GET filename
def ask_filename():
    filename = input("Enter your file name: ")
    # IF the user didn't input file extension, THEN
    if "." not in filename:
        # SET filename = filename + .py
        filename += ".py"
    return filename


# LOAD file contents line by line into a list of strings
def open_file(filename):
    try:
        input_file = open(filename)
        content_list = [line.rstrip() for line in input_file.readlines()]
        input_file.close()
    # IF the user input an unexisting filename, THEN
    except FileNotFoundError as error:
        # DISPLAY informing of the filename form
        print(error)
        # GET filename again
        filename = ask_filename()
        content_list = open_file(filename)
    finally:
        return content_list


# Function 1 - Style Rule 1
# GET a row (string) and the row index
def style_1(line, index):
    # SET error position as a list of numbers (indices)
    # SET error count = 0
    error_index_list = []
    error_count = 0
    checking_index = 0
    inserting_index = 0
    # FOR each character in the string,
    for i in range(len(line)):
        # IF the character is "(", THEN
        if line[i] == "(":
            # SET checker = index + 1
            checking_index = i + 1
            inserting_index = checking_index
        # ELSEIF the character is ")", THEN
        elif line[i] == ")":
            # SET checker = index - 1
            checking_index = i - 1
            inserting_index = i
        # ELSE, THEN
        else:
            # CONTINUE to the next iteration
            continue
        # GET a character at index checker
        # IF the second character is " ", THEN
        if line[checking_index] == " ":
            # CONTINUE to the next iteration
            continue
        # ELSE, THEN
        else:
            # SET error position to append the index of the missing spaces
            error_index_list.append(inserting_index)
            # INCREMENT error count
            error_count += 1
    # SET the string as █(ascii 219) inserted where the space(s) should be
    #     do this in a descending order so the rests won't be effected
    error_index_list.reverse()
    for insert in error_index_list:
        line = line[:insert] + chr(9608) + line[insert:]
    # PRINT ( the row index + 1 ) + the string + informing message
    if error_count != 0:
        print("line", index + 1, ":", line, "\t#", error_count, "missing space(s) between parenthesis")


def parenthesis_closer(line, index):
    opening = line.count("(")
    closing = line.count(")")
    if opening > closing:
        print("line", index + 1, ":", line, "\t#", opening - closing, "missing ')'")
    elif closing < opening:
        print("line", index + 1, ":", line, "\t#", closing - opening, "missing '('")


# DISPLAY header
print("""
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*   Style Detector ( for Style Rule 1 and unpairing parenthesis )   *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
""")
file_name = ask_filename()
file_content = open_file(file_name)
print("")
for i in range(len(file_content)):
    style_1(file_content[i], i)
    parenthesis_closer(file_content[i], i)
print("\nPlease note that this detector only indicates simple errors with parenthesis!\n")
