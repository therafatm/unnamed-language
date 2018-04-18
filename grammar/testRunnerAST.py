import os, subprocess, sys

def colored(s, c):
    color  = {"red":"\33[91m", "green": "\33[32m", "violet": "\33[35m"}
    return color[c] + s + "\033[0m"

path = "./tatestsnew/withoutSubtypes/"
verbose = False
if len(sys.argv) > 2:
    verbose = True

for f in os.listdir(path):
    if f.endswith(".ul"):
        print "Running compiler on " + f + ":"
        command = ["java", "Compiler", path + f]
        res = subprocess.check_output(command, stderr=subprocess.STDOUT)
        if f.startswith(".") is False:
            if len(res) > 0:
                print colored("Failed", "red")
                print colored(res, "red")
            else:
                print colored("Passed", "green")
                if verbose:
                    print colored(res, "violet")
        else:
            if len(res) > 0:
                print colored("Passed", "green")
                if verbose:
                    print colored(res, "violet")
            else:
                print colored("Failed", "red")
                print colored(res, "red")
        