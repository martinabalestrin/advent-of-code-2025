def rotations(line, oldDial):

    count = int(line[1:])
    direction = line[0]
    contZero = 0

    if direction == 'R':
        result = oldDial + count
        wraps = result // 100
        contZero += wraps
        if oldDial == 0 and result > 99: contZero -= 1
        result %= 100
        
        if result == 0 and oldDial + count == 0: contZero += 1

    elif direction == 'L':
        result = oldDial - count
        wraps = (abs(result) + 99) // 100 if result < 0 else 0
        contZero += wraps
        if oldDial == 0 and result < 0: contZero -= 1
        result %= 100
        
        if result == 0 and oldDial - count == 0: contZero += 1


    return result, contZero

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