# Objetivo: encontrar a maior voltagem possível para cada bateria e depois somar
# Verificar 

def max_voltage(bank):

    bank = bank.strip() # limpa a linha, removendo '\n'
    credits = len(bank) - 12 # 12 é a quantidade de dígitos de voltage
    largest_voltage = []

    for digit in bank:
        while largest_voltage and credits > 0 and largest_voltage[-1] < digit:
            # caso tenha créditos e o digito atual seja maior que o último da fila:
            # tira o ultimo, gasta um crédito e insere esse novo
            # topo = largest_voltate[-1] --> último da fila
            largest_voltage.pop()
            credits -= 1
        # adiciona o dígito maior
        largest_voltage.append(digit)

    # garantir que sejam exatamente 12 digitos
    largest_voltage = largest_voltage[:12]

    # debug
    # print (largest_voltage)

    return int("".join(largest_voltage))

def main():
    with open("Day 3/input.txt") as file:
        cont = 0

        for line in file:
            cont += max_voltage(line)

        print(cont)


if __name__ == "__main__":
    main()