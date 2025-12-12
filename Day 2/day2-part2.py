# Analisar todos os IDs do intervalo (guardar ID inicial e final)
# Para cada ID, testar para cada j número que vai até a metade do len(numStr) se os padrões se repetem
# Se as partes forem iguais, adicionar a um contador

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
        
        numStr = str(i)
        tam = len(numStr)

        # Testa para todos os números que são metade do 'tam'
        for j in range(1, (tam // 2 + 1)):
            if tam % j == 0:
                padrao = numStr[:j]
                if padrao * (tam // j) == numStr:
                    cont += i
                    break

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

        # Processar o último intervalo
        if intervalo:
            cont += procurarIDsInvalidos(intervalo)



    print(cont)


if __name__ == "__main__":
    main()