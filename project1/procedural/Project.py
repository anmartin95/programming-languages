import math

# run:
# python3 Project.py

#  --- python classes - used like structs ---
class Sun:
    def __init__(self, name, diameter, circumference, planets):
        self.name = name
        self.diameter = diameter
        self.circumference = circumference
        self.planets = planets
        self.volume = 0.0

class Planet:
    def __init__(self, name, diameter, circumference, orbitalPeriod, distanceFromSun, moons):
        self.name = name
        self.diameter = diameter
        self.circumference = circumference
        self.orbitalPeriod = orbitalPeriod
        self.distanceFromSun = distanceFromSun
        self.moons = moons
        self.volume = 0.0
        
class Moon:
    def __init__(self, name, diameter, circumference):
        self.name = name
        self.diameter = diameter
        self.circumference = circumference

# --- functions for calculations ---
def calcCircumference(d):
    return (d*math.pi)

def calcDiameter(c):
    return (c/math.pi)

def calcDistance(o):
    return pow((pow(o, 2)), (1.0/3.0))

def calcOrbitalPeriod(d):
    return pow(pow(d, 3), 0.5)

# data hardcode into "structs"
planet1 = Planet("Mercury", 0.0, 15329, 0.39, 0.0, [])
planet2 = Planet("Venus", 12104, 0.0, 0.0, .72, [])
moon1 = Moon("Luna", 3474, 10917)
planet3 = Planet("Earth", 12756, 40075, 1, 1, [moon1])
moon2 = Moon("Phobos", 22.5, 0.0)
moon3 = Moon("Deimos", 0.0, 39)
planet4 = Planet('Mars', 0.0, 21344, 0.0, 1.52, [moon2, moon3])
moon4 = Moon("Ganymede", 5268, 0.0)
moon5 = Moon("Callisto", 0.0, 4820.6)
moon6 = Moon("Io", 0.0, 3643.2)
planet5 = Planet("Jupiter", 142984, 0.0, 0.0, 5.2, [moon4, moon5, moon6])
planet6 = Planet("Saturn", 120536, 0.0, 0.0, 9.54, []) 
planet7 = Planet("Uranus", 51118, 0.0, 0.0, 19.2, [])
planet8 = Planet("Neptune",  49528, 0.0, 0.0, 30.06, [])
sun = Sun("Sol", 1400000, 0.0, [planet1, planet2, planet3, planet4, planet5, planet6, planet7, planet8])

totalPlanetVolume = 0.0

# handle and print sun data
print(" ---- Solar System ---- \nSun:")
if sun.diameter == 0.0 and sun.circumference != 0.0: # calc diameter given circumference
    sun.diameter = calcDiameter(sun.circumference)
elif sun.circumference == 0.0 and sun.diameter != 0.0: # calc circumference given diameter
    sun.circumference = calcCircumference(sun.diameter)
sun.volume = (4.0/3.0)*math.pi*pow((sun.diameter/2.0), 3) # calc volume
print(f"Name: {sun.name}, Diameter: {sun.diameter:,.2f} km, Circumference: {sun.circumference:,.2f} km, Planets: ")
# print(f"Volume: {sun.volume}") # test volume calc
# handle and print planet data
for p in sun.planets:
    if p.diameter == 0.0 and p.circumference != 0.0: # calc diameter given circumference
        p.diameter = calcDiameter(p.circumference)
    elif p.circumference == 0.0 and p.diameter != 0.0: #calc circumference given diameter
        p.circumference = calcCircumference(p.diameter)  
    if p.orbitalPeriod == 0.0 and p.distanceFromSun != 0.0: #calc orbital period given distance from the sun
        p.orbitalPeriod = calcOrbitalPeriod(p.distanceFromSun)
    elif p.distanceFromSun == 0.0 and p.orbitalPeriod != 0.0: #calc distance from the sun given orbital period
        p.distanceFromSun = calcDistance(p.orbitalPeriod)
    p.volume = (4.0/3.0)*math.pi*pow((p.diameter/2.0), 3)  #calc volume 
    totalPlanetVolume += p.volume # add volume to total planet var
    if p.moons == []:
        print(f"\tName: {p.name}, Diameter: {p.diameter:,.2f} km, Circumference: {p.circumference:,.2f} km, Distance from the Sun: {p.distanceFromSun:,.2f} au, Orbital Period: {p.orbitalPeriod:,.2f} yr")
    else:
        print(f"\tName: {p.name}, Diameter: {p.diameter:,.2f} km, Circumference: {p.circumference:,.2f} km, Distance from the Sun: {p.distanceFromSun:,.2f} au, Orbital Period: {p.orbitalPeriod:,.2f} yr Moons: ")
    # print(f"\tVolume: {p.volume}") # test volume calc
    # handle and print moon data
    for m in p.moons:
        if m.diameter == 0.0 and m.circumference != 0.0: # calc diameter given circumference
            m.diameter = calcDiameter(m.circumference)
        elif m.circumference == 0.0 and m.diameter != 0.0: #calc circumference given diameter
            m.circumference = calcCircumference(m.diameter) 
        print(f"\t\tName: {m.name}, Diameter: {m.diameter:,.2f} km, Circumference: {m.circumference:,.2f} km")
# print(f"Total Planet Volume: {totalPlanetVolume}") # test total vol calc
#handle and print total volume comparison
sunHasGreaterVolume = False
if sun.volume > totalPlanetVolume:
    sunHasGreaterVolume = True
else:
    sunHasGreaterVolume = False
print(f"All the planets’ volumes added together could fit in the Sun: {sunHasGreaterVolume}")


