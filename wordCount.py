file = open("./src/Line.java", 'r')

countWords_comments = 0
countLines_comments = 0
countWords_code = 0
countLines_code = 0

for line in file:
    strippedLine = line.strip()
    if (strippedLine.startswith("/") or strippedLine.startswith("*")):
        countLines_comments += 1
        for word in line.split():
            countWords_comments += 1
        continue
    countLines_code += 1
    for word in line.split():
        countWords_code += 1
print("Lines of code:\n")
print("There are " + str(countLines_code) + " lines.\nComprised of " + str(countWords_code) + " words.\n")
print("Lines of comments:\n")
print("There are " + str(countLines_comments) + " lines.\nComprised of " + str(countWords_comments) + " words.\n")
