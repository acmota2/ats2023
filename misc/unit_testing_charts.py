import matplotlib.pyplot as plt
import numpy as np

field_number = int(input('Quantos campos pretende inserir? '))

labels = []
sizes = []
missed = []
means = {}

max = 0
for i in range(1, field_number+1):
    label = input(f'Nome do campo {i}? ')
    labels.append(label)
    
    size = input(f'Numero de linhas de {i}? ')
    size = int(size)
    max += size
    sizes.append(size)
    
    missed.append(int(input(f"Numero de linhas sem coverage em {1}? ")))

sizes_percent = [(x / max) * 100 for x in sizes]
means = {
    'With coverage': (*(((x - y) / x * 100) for x, y in zip(sizes, missed)),),
    'Without coverage': (*(y / x * 100 for x, y in zip(sizes, missed)),)
}

fig, axes = plt.subplots(4,1)

plt.figure(0)
axes[0].pie(sizes_percent, labels=labels, autopct='%1.1f%%')

plt.figure(1)

x = np.arange(len(labels))
width = 0.2
multiplier = 0

for attribute, measurement in means.items():
    offset = width * multiplier
    rects = axes[1].bar(x + offset, measurement, width, label=attribute)
    axes[1].bar_label(rects, padding=3)
    multiplier += 1

axes[1].set_ylabel('Coverage (%)')
axes[1].set_xticks(x + width, labels)
axes[1].legend(loc='upper left', ncols=2)
axes[1].set_ylim(0, 100)

plt.figure(2)
sum_sizes_percentage = sum(sizes_percent) / field_number
axes[2].pie([sum_sizes_percentage, 100 - sum_sizes_percentage], labels=['Covered', 'Not covered'], autopct='%1.1f%%')

plt.figure(3)
data = {x: y for x, y in zip(labels, sizes)}
print(data)
classes = list(data.keys())
values = list(data.values())
  
fig = plt.figure(figsize = (10, 5))
 
plt.bar(classes, values, color ='blue', width = 0.25)
 
plt.xlabel("Classes em teste")
plt.ylabel("Número de linhas")

plt.show()
