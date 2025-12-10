def rotations(line, oldDial):

    count = int(line[1:])
    direction = line[0]
    contZero = 0

    if direction == 'R':
        result = oldDial + count

        while result > 99:
            result = result - 100

    elif direction == 'L':
        result = oldDial - count

        while result < 0:
            result = result + 100

    if result == 0: contZero += 1

    return result, contZero

def main():
    with open("Day 1/input.txt") as file:
        oldDial = 50
        cont = 0
        for line in file:
            newDial, contZero = rotations(line, oldDial)
            cont = cont + contZero
            
            # Debug
            # print(line.strip(), " - ", returnRotation[0], " - ", cont)

            oldDial = newDial

    print(cont)

if __name__ == "__main__":
    main()