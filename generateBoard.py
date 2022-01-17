#!/usr/bin/env python3

map = {
    "0": "BLANK",
    "1": "ONE",
    "2": "TWO",
    "3": "THREE",
    "4": "FOUR",
    "5": "FIVE",
    "6": "SIX",
    "7": "SEVEN",
    "8": "EIGHT",
    "9": "NINE",
    "A": "A",
    "B": "B",
    "C": "C",
    "D": "D",
    "E": "E",
    "F": "F",
    "G": "G"
}

# data = ["0F0D08B90417C00G",
#         "71000000D06C00BE",
#         "0000000E9G300875",
#         "0900002C8000F004",
#         "40309E00020D8G01",
#         "58A0DB07000FE200",
#         "20B0051030A09C00",
#         "F06100C0E000A00B",
#         "0600C08G0E0B0300",
#         "00D00A6240000009",
#         "A00E030D0609G780",
#         "00C0000401GABD00",
#         "000F20D00000003C",
#         "02000000B9080000",
#         "6005007BF0E000A8",
#         "GC0450E003007000"]

data = ["E7C3452F9BA81D6G",
"F1A978ED6G45B23C",
"524G3AB6FD1CE798",
"D8B6C9G127E3FA54",
"3A179D48CEBF26G5",
"8G6DEB521A3794CF",
"B4F2AG1C8659D37E",
"95EC67F3G2D481BA",
"AD98513E74C2GBF6",
"4FGB2C87E561A9D3",
"1635B4A9D8FG7CE2",
"7C2EDF6GB39A5841",
"C3DF8E9A41GB6527",
"698AG2C53F7D4E1B",
"2B71F6D45C8E3GA9",
"GE54137BA926CF8D",
]

for i in range(len(data)):
    for j in range(len(data[i])):
        if (data[i][j] == '0'):
            continue
        print("board.set(" + str(i) + ", " + str(j) + ", Token." + map[data[i][j]] + ");")
