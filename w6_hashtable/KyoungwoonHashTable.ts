type HashTableItem<K, V>  = { key: K, value: V }


class HashTable<K extends number, V> {
  table: (HashTableItem<K, V> | null)[]
  private size: number

  constructor() {
    this.size = 3
    this.table = new Array(this.size).fill(null)
  }


  findSlot(key: K): number | 'full' {
    let index = this.hash(key)
    const start = index

    while(this.table[index] && this.table[index].key !== key) {
      index = (index + 1) % this.size
      if (index === start) {
        return 'full'
      }
    }

    return index
  }

  // size 초과 시 용량 늘리기
  set(key: K, value: V) {
    let index = this.findSlot(key)

    if (index === 'full') {
      this.resize()
      index = this.findSlot(key)
    }

    if (this.table[index]) {
      this.table[index]!.value = value
    } else {
      this.table[index] = {
        key,
        value,
      }
    }

    return key
  }

  search(key: K) {
    const index = this.findSlot(key)

    if (index === 'full') {
      return null
    }

    return this.table[index] ?? null
  }

  // TODO. resize 하면서 기존 slot 위치 조정이 필요함
  private resize() {
    this.table.concat(new Array(this.size).fill(null))
    this.size *= 2
  }

  remove(key: K) {
    let index = this.findSlot(key)

    if (index === 'full' || !this.table[index]) {
      return null
    }

    let j = index
    while(true) {
      this.table[index] = null
      while(true) {
        j = (j + 1) % this.size
        if (!this.table[j]) {
          return key
        }

        const k = this.hash(this.table[j]!.key)
        if (k < index && index <= j) {
          break
        }
      }

      this.table[index] = this.table[j]
      index = j
    }
  }

  private hash(key: K) {
    return key % this.table.length
  }
}

const hash = new HashTable()
hash.set(1, 1)
hash.set(2, 2)
hash.set(99, 99)
console.log(hash.table)
console.log(hash.search(99))
