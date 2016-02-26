#mainly junk code to test Julias functionality
#add two numbers
function add(a,b)
  return a + b
end

add(-12,68)

#create a "Person" object
type Person
  name
  age
  height
end

sean = Person("Sean",24,5.11)
sean.name

sean.height


brent = Person("Brent",24,5.09)

#create a list of Person objects called people
people = [sean, brent]
people[2]
people[2].height

people[1].weight

#create a basic function
function compareHeight(person1, person2)
  if person1.height > person2.height
    println (person1.name, " is taller!")
  else
    println (person2.name, " is taller!")
  end
end

compareHeight(sean, brent)

#test some IO functions
function input(prompt::String="")
        print(prompt)
        chomp(readline())
end


