def checksum(input_lines):
    count_twice = 0
    count_thrice = 0

    # process all input
    for line in input_lines:
        charmap = {}
        # build char map
        for c in line:
            if c in charmap:
                charmap[c] = charmap[c] + 1
            else:
                charmap[c] = 1
        # check for frequencies
        found_two = False
        found_three = False
        for k,freq in charmap.items():
            if freq == 2 and not found_two:
                count_twice += 1
                found_two = True
            elif freq == 3 and not found_three:
                count_thrice += 1
                found_three = True
            if found_two and found_three:
                break


    # get checksum
    return count_thrice * count_twice

if __name__ == "__main__":
    with open('day2_input.txt') as f:
        lines = f.readlines()
    lines = [x.strip() for x in lines]
    print(checksum(lines))