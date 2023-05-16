import matplotlib.pyplot as plt
import numpy as np

field_number = input('Quantos campos pretende inserir? ')

labels = []
sizes = []
missed = []
means = {}

max = 0
for i in range(1, int(field_number)+1):
    label = input(f'Nome do campo {i}? ')
    labels.append(label)
    
    size = input(f'Numero de linhas de {i}? ')
    size = int(size)
    max += size
    sizes.append(size)
    
    missed = input(f"Numero de linhas sem coverage em {1}? ")

sizes = [(x / max) * 100 for x in sizes]
missed = [(x / max) * 100 for x in sizes]
means = {
    'With coverage': (*(x - y for x, y in zip(sizes, missed)),),
    'Without coverage': (*(x for x in missed),)
}



fig, axes = plt.subplots(3,1)

plt.figure(0)
axes[0].pie(sizes, labels=labels, autopct='%1.1f%%')

plt.figure(1)


x = np.arange(len(labels))
width = 0.2  # the width of the bars
multiplier = 0

for attribute, measurement in means.items():
    offset = width * multiplier
    rects = axes[1].bar(x + offset, measurement, width, label=attribute)
    axes[1].bar_label(rects, padding=3)
    multiplier += 1

# Add some text for labels, title and custom x-axis tick labels, etc.
axes[1].set_ylabel('Coverage (%)')
axes[1].set_xticks(x + width, labels)
axes[1].legend(loc='upper left', ncols=2)
axes[1].set_ylim(0, 300)

plt.figure(2)
sum_sizes_percentage = sum(sizes)
sum_missed = sum(missed)
axes[2].pie([sum_sizes_percentage, sum_missed], labels=['Covered', 'Not covered'], autopct='%1.1f%%')

plt.show()
