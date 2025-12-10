def rotations(line, oldDial):

    count = int(line[1:])
    direction = line[0]
    contZero = 0
    newDial = oldDial

    if direction == 'R':
        for i in range(count):
            newDial += 1
            if newDial == 100: newDial = 0
            if newDial == 0: contZero += 1

    elif direction == 'L':
        for i in range(count):
            newDial -= 1
            if newDial == -1: newDial = 99
            if newDial == 0: contZero += 1


    return newDial, contZero

def main():
    with open("Day 1/input.txt") as file:
        oldDial = 50
        cont = 0

        for line in file:
            newDial, contZero = rotations(line, oldDial)
            cont += contZero
            
            # Debug
            print(line.strip(), " - ", newDial, " - ", cont)

            oldDial = newDial

    print(cont)

if __name__ == "__main__":
    main()