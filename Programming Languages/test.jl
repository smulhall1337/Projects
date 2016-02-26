# Welcome.

# This is a regular file, which you can edit as much as you want,
# then save for later or discard. You can open a fresh copy by
# pressing `Ctrl-Space` and typing `tutorial` followed by `Enter`.

# (and if you remember one thing, make it `Ctrl-Space` – you can
# get to everything in Juno via the command bar that pops up)

# Go to the `View->Console` menu to see any output. If you have
# issues, please let me know at http://discuss.junolab.org/

# ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––

function add(a,b)
  return a + b
end

add(-12,68)

type Person
  name
  age
  height
end

sean = Person("Sean",24,5.11)
sean.name

sean.height


brent = Person("Brent",24,5.09)

people = [sean, brent]
people[2]
people[2].height

people[1].weight

function compareHeight(person1, person2)
  if person1.height > person2.height
    println (person1.name, " is taller!")
  else
    println (person2.name, " is taller!")
  end
end

compareHeight(sean, brent)

function input(prompt::String="")
        print(prompt)
        chomp(readline())
end


