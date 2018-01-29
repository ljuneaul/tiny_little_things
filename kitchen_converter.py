"""
get input
if imperial exist, get imperial_value
else, ask for input again
"""
import sys


data_str = """oz ml 29.5735
pt ml 473.176
lb g 453.592
tsp ml 5
Tbsp ml 15"""

input_str = "8 oz to ml"


# convert data string to a list of lists
def data_to_list(aStr):
    aList = []
    count = 0
    for line in aStr.split("\n"):
        aList.append(line.split(" "))
        aList[count][2] = float(aList[count][2])
        count += 1
    return aList


# convert user input to a handelable list
def input_to_list():
    aStr = input("Enter a request: ")
    aList = []
    for data in data_list:
        if data[0] in aStr and data[1] in aStr:
            aList = aStr.split(data[0])
            try:
                result = float(aList[0]) * data[2]
                print(str(result) + " " + data[1])
                input_to_list()
            except ValueError:
                print("You could only change " + possible_input_str)
                input_to_list()
    if len(aStr) > 0:
        print("Please use a valid input. (e.g. 8 oz to ml)")
        input_to_list()
    sys.exit("Happy cooking!")


data_list = data_to_list(data_str)
possible_input_list = []
for data in data_list:
    possible_input_list.append(data[0])
possible_input_str = ", ".join(possible_input_list)

user_input = input_to_list()
