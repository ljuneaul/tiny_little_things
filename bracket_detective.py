# author : https://github.com/njavig
# in progress (almost)

# IPO chart
# input: a filename
# processing: load file, chop line by line
#             Style Rule 1: Put a space after an open parenthesis "(" and
#                           one before a close parenthesis ")".
#                           -> check if there is space between ( and )
#             Style Rule 2: Put a space before and after each operator.
#                           -> check if an operator is between spaces
# output: if there is (an) error(s), tell user where it was, what it was.
#         if there is no error, end the program and inform about other rules


# next patch: negation ('-' + num)
#             bulk quote through multiple lines


import sys


# GET filename
def ask_filename():
    filename = input("Enter your file name: ")
    # IF the user didn't input file extension, THEN
    if len(filename) == 0 or filename == "exit":
        sys.exit()
    elif "." not in filename:
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
    global global_error_count

    # quote open/close handling
    current_quote = ""
    is_quote_opened = False

    # FOR each character in the string,
    for i in range(len(line)):
        # quote open/close handling
        if line[i] in ('"', "'"):
            if not is_quote_opened:
                current_quote = line[i]
                is_quote_opened = True
            elif line[i] == current_quote:
                is_quote_opened = False
            continue

        if not is_quote_opened:
            # IF the character is "#", THEN
            if line[i] == "#":
                # END for loop (because the rest is comments)
                break
            # ELSEIF the character is "(", THEN
            elif line[i] == "(":
                # SET checker = index + 1
                checking_index = i + 1
                inserting_index = checking_index
                correct = (" ", ")")
            # ELSEIF the character is ")", THEN
            elif line[i] == ")":
                # SET checker = index - 1
                checking_index = i - 1
                inserting_index = i
                correct = (" ", "(")
            # ELSE, THEN
            else:
                # CONTINUE to the next iteration
                continue
            # GET a character at index checker
            # IF the second character is " ", THEN
            try:
                if line[checking_index] in correct:
                    # CONTINUE to the next iteration
                    continue
                # ELSE, THEN
                else:
                    # SET error position to append the index of the missing spaces
                    error_index_list.append(inserting_index)
                    # INCREMENT error count
                    error_count += 1
            except IndexError:
                pass
    # SET the string as █(ascii 9608) inserted where the space(s) should be
    #     do this in a descending order so the rests won't be effected
    error_index_list.reverse()
    for insert in error_index_list:
        line = line[:insert] + chr(9608) + line[insert:]
    # PRINT ( the row index + 1 ) + the string + informing message
    if error_count != 0:
        message = "missing space(s) between parenthesis"
        print("line", index + 1, ":", line, "\t#", error_count, message)
        global_error_count += error_count


# Extra function: check if there is any unpairing parenthesis_closer
# GET a row (string) and the row index
def parenthesis_closer(line, index):
    # SET the line to be connected with previous one if there was "\" before
    global temp
    global global_error_count
    line = temp + "line " + str(index + 1) + " : " + line
    if line[-1:] == chr(92):
        temp = line + "\n"
    else:
        # GET how many "(" and ")" each
        opening = line.count("(")
        closing = line.count(")")
        difference = 0
        # IF the number is different, THEN DISPLAY the amount
        if opening > closing:
            difference = opening - closing
            print(line, "\t#", str(difference) + " missing ')'")
        elif closing < opening:
            difference = closing - opening
            print(line, "\t#", str(difference) + " missing '('")
        global_error_count += difference
        temp = ""


# operator = list of (sign, 1-digit w/ spaces, 2-digit w/ spaces)
operator = []
operator.append(("+", " + ", " += "))
operator.append(("-", " - ", (" -= ", " -> ")))
operator.append(("*", (" * ", '"*"'), (" *= ", " ** ")))
operator.append(("/", (" / ", "m/h"), (" /= ", " // ")))
operator.append(("%", " % ", " %= "))
operator.append(("=", (" = ", "+= ", "-= ", "*= ", "/= ", "%= ", ">= ", "<= "), (" == ")))
operator.append((">", (" > ", "-> "), " >= "))
operator.append(("<", " < ", " <= "))


# Function 2 - Style Rule 2
# GET a row (string) and the row index
def style_2(line, index):
    global global_error_count
    for i in range(len(operator)):
        # IF 1-bit operators in the string and space missing, THEN
        if operator[i][0] in line:
            # GET its position
            position = line.find(operator[i][0])
            should_check = True
            for quote in ("'", '"'):
                num_quote_before = line[:position - 1].count(quote)
                num_quote_after = line[position + 1:].count(quote)
                if num_quote_after != 0 and num_quote_after % 2 != 0\
                   and num_quote_after == num_quote_before:
                    should_check = False
                elif "#" in line[:position - 1]:
                    should_check = False
            if should_check:
                # IF any space is missing around the operator(consider 2-bit), THEN
                if line[position - 1:position + 2] not in operator[i][1]\
                   and line[position - 1:position + 3] not in operator[i][2]:
                    # DISPLAY its index
                    message = "missing space(s) around (an) operator(s)"
                    print("line", index + 1, ":", line, "\t#", message)
                    global_error_count += 1
                    # to prevent multiple print in 2-digit, terminate the iteration
                    return


# DISPLAY header
print("""
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*   bracket detective  ( for Style Rule 1, 2, and unpairing parenthesis )   *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
""")
# LOAD file contents line by line into a list of strings
file_name = ask_filename()
# LOAD file contents line by line into a list of strings
file_content = open_file(file_name)
temp = ""
global_error_count = 0
print("")
# IF there is (an) error(s) for each line, THEN DISPLAY
for i in range(len(file_content)):
    style_1(file_content[i], i)
    parenthesis_closer(file_content[i], i)
    style_2(file_content[i], i)
# DISPLAY ending message
# END the program
if global_error_count == 0:
    each_end_message = "No error found!"
else:
    each_end_message = "\nInvestigation ended. Please fix "\
                       + str(global_error_count) + " error(s) by yourself."
common_end_message = "Please note that this detector only indicates simple \
errors with parenthesis and spaces!"
print(each_end_message)
print(common_end_message, "\n")


# Dear Stephen,

# If you are reading this by any chance, please be merciful and kindly forgive
# me that this program itself is totally against the style rule 1.
# I am using an editor named Atom at my home only for non-assignment-related
# tasks and it throws tons of red and yellow warnings filling the whole screen
# scarily when I follows that rule. I really appreciate the concept and
# totally understand why I should follow the rules. And I swear I will never
# question related this matter since I totally obey your rule in the classroom.

# Yours Sincerely,
# J
