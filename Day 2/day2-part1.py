# Analisar todos os IDs do intervalo (guardar ID inicial e final)
# Para cada ID, separar em metadeFrente e metadeTras
# Se metadeFrente e metadeTras forem iguais, adicionar a um contador

def procurarIDsInvalidos(intervalo):

    i = intervalo.find("-")
    idInicial = int(intervalo[:i])
    idFinal = int(intervalo[i+1:])
    cont = 0

    # Debug
    # print("Intervalo: ", intervalo)
    # print("ID Inicial: ", idInicial)
    # print("ID Final: ", idFinal)

    for i in range(idInicial, idFinal + 1):

        if len(str(i)) % 2 == 0:
            tam = len(str(i)) // 2
            metadeFrente = str(i)[:tam]
            metadeTras = str(i)[tam:]

            if metadeFrente == metadeTras:
                cont += int(str(metadeFrente) + str(metadeTras))

    return cont

def main():

    with open("Day 2/input.txt") as file:
        cont = 0  # Contabilizar a soma dos IDs
        intervalo = ""

        for line in file:
            for char in line:
                if char == ",": 
                    cont += procurarIDsInvalidos(intervalo)
                    intervalo = ""
                else:
                    intervalo += char

    print(cont)


if __name__ == "__main__":
    main()