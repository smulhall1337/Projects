# Welcome.
#
# THis was our team project for programming languages using the Julia language
#The task was to create a career questionare (seperate from the "Faker" file. this was the actual project)
#A user would answer a series of questions and, based on a score, the program would issue a job (or jobs) 
#that the user could fit into
#
#different answers affect jobs differently
#need to be a people person in retail, but its ok if they cant do manual labor too much, so it wont affect the score as much
#but some things might disqualify you completely(ex: college degree)
#some wont even matter
#things that are marked (need) are necessities to the job and the user must answer yes
#order of importance from greatest to least
#Retail: people person(need), feet(need), fast paced, manual labor, college ed, high school ed, overnight (works outside)
#Construction: works outside(need), manual labor(need), feet(need), fast pased, overnight, people person, college ed, hs ed
#Nurse: feet(need), people, college ed(need), fast pased, overnight, manual labor, hs ed(need), works outside
#Programmer: College ed, people person, hs ed(need), fast pased, (overnight, feet, manual labor, works outside)
#engineer: College ed(need), hs ed(need), (fast pased, manual labor, people, feet, works outside, overnight )
#law:people person, works outside, highschool ed(need (i think)), overnight, manual labor, college ed, feet, fast pased,
workspace()

type job #framework for the job type
  name
  score #current score for the job, if this falls below zero, the job will be locked
  locked #if the user doesnt meet a (need), then we set this true and prevent the user from seeing this job
end

retail = job("Retail Clerk", 0, false)
construction = job("Construction", 0, false)
nurse = job("Nurse", 0, false)
programmer = job("Programmer", 0, false)
engineer = job("Engineer", 0, false)
law = job("Law Enforcement", 0, false)

function main()
  println("Welcome! Please answer the following questions with either a 'Yes' or No'")
  println("")

  println("Do you work well with other people?")
    response = string(readline())
    if response == "yes"
      retail.score + 8
      construction.score + 3
      nurse.score + 7
      programmer.score + 7
      law.score + 8
      engineer.score + 7

    elseif response == "no"
      retail.locked = true
      retail.score - 8
      construction.score - 3
      nurse.score - 7
      programmer.score - 7
      law.score - 8
  end

  println("Are you able to stand on your feet for an extended period of time?")
  response = readline()
    if response == "yes"
    retail.score + 7
    construction.score + 6
    nurse.score + 8
    programmer.score + 3
    law.score + 2
    engineer.score + 4

  elseif response == "no"
    nursel.locked = true
    construction.locked = true
    retail.locked = true
    law.score -2
  end

  println("Are you able to work well in a fast paced environment?")
  response = readline()
    if response == "yes"
      retail.score + 6
      construction.score + 5
      nurse.score + 5
      programmer.score + 5
      law.score + 1
      engineer.score + 6

  elseif response == "no"
      retail.score - 6
      construction.score - 5
      nurse.score - 5
      programmer.score - 5
      law.score - 1
      engineer.score - 6
  end


  println("Are you able to perform basic acts of manual labor? (eg. lifting up to 40 pounds?")
  response = readline()
    if response == "yes"
      retail.score + 5
      construction.score + 7
      nurse.score + 3
      programmer.score + 2
      law.score + 4
      engineer.score + 5
    elseif response == "no"
      construction.locked = true
      retail.score - 4
      nurse.score - 3
      law.score - 4
    end


  println("Have you obtained your GED?")
  response = readline()
    if response == "yes"
      println("Have you obtained, or are in the process of obtaining, your college degree?")
      response = readline()
    if response == "yes"
          retail.score + 4
          construction.score + 2
          nurse.score + 6
          programmer.score + 8
          law.score + 3
          engineer.score + 8
  elseif response == "no"
          engineer.locked = true
          nurse.locked = true
          retail.score + 3
          programmer.score + 6
          law.score + 2
          construction.score + 1
  end
  else
      programmer.locked = true
      engineer.locked = true
      nurse.locked = true
      law.locked = true
      construction.score -1
      retail.score -3
end
end
main()

jobs = Set(["Nurse", "Retail Clerk", "Construction Worker", "Engineer", "Law Enforcement", "Programmer"])

if nurse.locked || nurse.score < -5
  jobs = delete!(jobs, "Nurse")
end
if construction.locked || construction.score < -5
  jobs = delete!(jobs, "Construction Worker")
end
if law.locked || law.score < -5
  jobs = delete!(jobs, "Law Enforcement")
end
if engineer.locked || engineer.score < -5
  jobs = delete!(jobs, "Engineer")
end
if programmer.locked || programmer.score < -5
  jobs = delete!(jobs, "Programmer")
end
if retail.locked || retail.score < -5
  jobs = delete!(jobs, "Retail Clerk")
end


#jobPairs = collect(jobs)
#jobValues = collect(values(jobs))
#indexes = sort(collect(values(jobs)), rev = true)
#sortedNames = [jobPairs[i] for i in indexin(indexes, jobValues)]

#sortedNames[1]
print(jobs)

println("The jobs you are best suited for:" )
for i in jobs
  print("$i: ")
    print("$jobs")
  println("")
end
