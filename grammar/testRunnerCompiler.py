import os, subprocess, sys, time

def colored(s, c):
    color  = {"red":"\33[91m", "green": "\33[32m", "violet": "\33[35m"}
    return color[c] + s + "\033[0m"

paths = ["./tests/a4Tests"]

for path in paths:
    print "Running in " + path
    for f in os.listdir(path):
        if f.startswith(".") is False:
            fname = f[0:len(f)-3] + ".j"
            print "Running compiler on " + f + ":"
            command = ["java", "Compiler", path + "/" + f]
            res = subprocess.check_output(command)
            
            f = open(fname, "w+")
            f.write(res)
            f.close()

            command = ["java", "jasmin.Main", fname]
            res = subprocess.check_output(command)

            fname = fname[0: len(fname)-2]
            command = ["java", fname]
            res = subprocess.check_output(command)
 
            if len(res) > 0:
                print colored("Passed", "green")
                print "Output:"
                print colored(res, "violet")
            else:
                print colored("Failed", "red")
                print colored(res, "violet")
