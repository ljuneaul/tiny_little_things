# I, Jungwon Lim, student number 000772198, certify that all code submitted is my own work;
# that I have not copied it from any other source.  I also certify that I have not allowed
# my work to be copied by others.

# This program compares the effect of driving at different speeds 
# on your fuel consumption.  The user will enter the distanceance of 
# their drive in kms and the cost of gas per liter.  The 
# program will report back the cost of doing that drive at
# different speeds.

# miter -> m
# kilometer -> km

distance = float( input( "How long is your daily drive in kms? " ) )
price_per_liter = float( input( "How much does gas cost per liter? " ) )
miles_per_gallon = float( input( "What is your car's miles per gallon rating? " ) )

# 0.6 mile = 1 km, 1 US gallon = 3.78 L, approximately
# convert miles per gallon to kms per liter
miles_per_gallon_to_km_per_liter = miles_per_gallon / 0.6 / 3.78

# milage tables start at 55mph, need to convert to kph
price_per_day = price_per_liter * ( distance / miles_per_gallon_to_km_per_liter )

# calculations for diving at 100 km/h
price_at_100km_per_hour = round( price_per_day * 1.03, 2 )
print( "\nIf you drive at " + str( 60 * 1.6 ) + " km/h it will cost price_" + \
str( price_at_100km_per_hour ) + " per day" )
print( "That's price_" + str( round( price_at_100km_per_hour * 365.4,2 ) ) + " per year.\n" )

# calculations for diving at 120 km/h
price_at_120km_per_hour = round( price_per_day * 1.23, 2 )
print( "If you drive at " + str( 75 * 1.6 ) + " km/h it will cost price_" + \
str( price_at_120km_per_hour ) + " per day" )
print( "That's price_" + str( round( price_at_120km_per_hour * 365.4,2 ) ) + " per year.\n" )

# show the difference in cost
print( "That's a difference of " + \
str( round( ( price_at_120km_per_hour - price_at_100km_per_hour ) * 365.4, 2 ) ) + \
" per year to drive 20 km/h faster." )

# calculate the time for the trip
# how many minutes to drive distance at 100 kph?
minutes_to_drive_at_100km_per_hour = distance * ( 60 / 100 )
# how many minutes to drive distance at 120 kph?
minutes_to_drive_at_120km_per_hour = distance * ( 60 / 120 )

# calculate and show the difference in time
time_difference = minutes_to_drive_at_100km_per_hour - minutes_to_drive_at_120km_per_hour
print( "You save " + str( time_difference ) + " minutes per day." )
