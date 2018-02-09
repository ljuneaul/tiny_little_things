"""
author: https://github.com/njavig
date:   2018-01-21

https://udel.edu/~os/riddle.html
The question is: Who owns the fish?

Hints
* the Brit lives in the red house   # opt_dic[“Brit”] == opt_dic[“red”]
* the Swede keeps dogs as pets
* the Dane drinks tea
* the green house is on the left of the white house
* the green house's owner drinks coffee
* the person who smokes Pall Mall rears birds
* the owner of the yellow house smokes Dunhill
* the man living in the center house drinks milk
* the Norwegian lives in the first house
* the man who smokes blends lives next to the one who keeps cats
* the man who keeps horses lives next to the man who smokes Dunhill
* the owner who smokes BlueMaster drinks beer
* the German smokes Prince
* the Norwegian lives next to the blue house
* the man who smokes blend has a neighbor who drinks water


House: 0, 1, 2, 3, 4
Color: white, red, yellow, green, blue
Nation: Brit, Swede, Dane, German, Norwegian
Beverage: tea, milk, beer, coffee, water
Cigar: Pall Mall, Dunhill, BlueMaster, Prince, blends
Pet: horses, dogs, cats, birds, fish

Get house list
Get color list
Get nation list
Get beverage list
Get cigar list
Get pet list

Set option_list as every option possible in [(red, brit, … ), ()]
Set option_dic in {red: 0, brit: 0, … } form

Get hints
If option meets hints:
    Return option
Else:
    check next option
"""
import itertools

color = ["white", "red", "yellow", "green", "blue"]
nation = ["Brit", "Swede", "Dane", "German", "Norwegian"]
beverage = ["tea", "milk", "beer", "coffee", "water"]
cigar = ["Pall Mall", "Dunhill", "BlueMaster", "Prince", "blends"]
pet = ["horses", "dogs", "cats", "birds", "fish"]

# generate a global permutation pattern
options = itertools.permutations(range(5), 5)
aOption = []
for option in options:
    aOption.append(option)

opt_dic = {}
for option in aOption:  # iterate through each case nested by each other cases
    for i in range(len(option)):
        opt_dic[color[option[i]]] = i
    if opt_dic["green"] != opt_dic["white"] - 1:
        continue
    for option in aOption:
        for i in range(len(option)):
            opt_dic[nation[option[i]]] = i
        if opt_dic["Norwegian"] != 0\
           or opt_dic["Brit"] != opt_dic["red"]\
           or opt_dic["Norwegian"]\
           not in (opt_dic["blue"] + 1, opt_dic["blue"] - 1):
            continue
        for option in aOption:
            for i in range(len(option)):
                opt_dic[beverage[option[i]]] = i
            if opt_dic["milk"] != 2\
               or opt_dic["Dane"] != opt_dic["tea"]\
               or opt_dic["green"] != opt_dic["coffee"]:
                continue
            for option in aOption:
                for i in range(len(option)):
                    opt_dic[cigar[option[i]]] = i
                if opt_dic["yellow"] != opt_dic["Dunhill"]\
                   or opt_dic["BlueMaster"] != opt_dic["beer"]\
                   or opt_dic["German"] != opt_dic["Prince"]\
                   or opt_dic["blends"]\
                   not in (opt_dic["water"] + 1, opt_dic["water"] - 1):
                    continue
                for option in aOption:
                    for i in range(len(option)):
                        opt_dic[pet[option[i]]] = i
                    if opt_dic["Swede"] == opt_dic["dogs"]\
                       and opt_dic["Pall Mall"] == opt_dic["birds"]\
                       and opt_dic["blends"]\
                       in (opt_dic["cats"] + 1, opt_dic["cats"] - 1)\
                       and opt_dic["horses"]\
                       in (opt_dic["Dunhill"] + 1, opt_dic["Dunhill"] - 1):
                        print(opt_dic, "found!")
                        print("Fish were at " + str(opt_dic["fish"] + 1) + "th house.")
                        break
                    else:
                        print(opt_dic)
