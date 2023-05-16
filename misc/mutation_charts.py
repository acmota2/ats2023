import matplotlib.pyplot as plt

field_number = int(input('Quantos mutation tests foram feitos? '))
mutation_won = int(input('Quantos passaram aos unit tests? '))

fig, ax = plt.subplots()
mutations_won = (field_number - mutation_won) / field_number
ax.pie([mutations_won, 100 - mutations_won], labels=['Passou', 'Não passou'], autopct='%1.1f%%')
plt.show()