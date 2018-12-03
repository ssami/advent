def frequency(input):
    sum = 0
    for i in input:
        sum += i
    return sum


def first_seen_dupe(input):
    nums = set()
    sum = 0
    index = 0
    while True:
        curr = input[index]
        sum += curr # add freq to set
        print(sum)
        if sum in nums:
            return sum
        nums.add(sum)
        if index == len(input)-1:
            index = 0
        else:
            index += 1


if __name__ == "__main__":
    with open('/Users/ssami/Documents/Personal/advent1_input.txt') as f:
        lines = f.readlines()
    stripped_lines = [int(x.strip()) for x in lines]
    print(stripped_lines)
    print(frequency(stripped_lines))
    print('First seen dupe: ', first_seen_dupe(stripped_lines))