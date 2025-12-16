# Objetivo: encontrar a maior voltagem possível para cada bateria e depois somar

def max_voltage(bank):
    biggest = 0
    for i in range(len(bank)):
        for j in range(i+1, len(bank)):
            num = int(str(bank[i]) + str(bank[j]))
            if (num > biggest): biggest = num

    return biggest

def main():
    with open("Day 3/input.txt") as file:

        cont = 0

        for line in file:
            cont += max_voltage(line)

        print(cont)


if __name__ == "__main__":
    main()