print( "\nThis program calculates Riemann sum of y = x ^ 2 graph with two x positions .\n" )

# input: 1) x ( from user )
x1 = float( input( "input a number for x1: " ) )
x2 = float( input( "input a number for x2: " ) )
x = x2 - x1
# 2) (optional) divide ea
ea = int( input( "input how many squares: " ) )



# process:
# 0) given function graph: y = x^2
# 1) square ( input -> input )
def y(x):
    result = x ** 2
    return result

# 2) cube ( input -> input )
def cube(x):
    result = x ** 3
    return result

# 3) gauge = x / ea
# 4) for range( 1, ea + 1 ), calculate and add up the area
def estimated_area( x ):
    guage = x / ea
    total_area = 0
    for n in range( 1, ea + 1 ):
        total_area += guage * y( guage * n + x1 )
    return abs( round( total_area, 2 ) )

# 5) actual area
def equation(x):
    result = ( 1 / 3 ) * cube( x )
    return result

def actual_area(x):
    result = equation( x2 ) - equation( x1 )
    return abs( round( result, 2 ) )



# output: 1) estimated area
print( "\nThe extimated area using Riemann sum is:", estimated_area( x ) )

# 2) actual area ( 1/3 * x ^ 3 )
print( "The actual area is:", actual_area( x ), "\n" )
