# Welcome.

# This is a regular file, which you can edit as much as you want,
# then save for later or discard. You can open a fresh copy by
# pressing `Ctrl-Space` and typing `tutorial` followed by `Enter`.

# (and if you remember one thing, make it `Ctrl-Space` – you can
# get to everything in Juno via the command bar that pops up)

# Go to the `View->Console` menu to see any output. If you have
# issues, please let me know at http://discuss.junolab.org/

# ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––

type job #the framework for our job type
  name #name (will be a string)
  #everything else will be a boolean
  worksOutside #job involves working outside
  fastPaced #fast paced
  peoplePerson #involves working with people
  feet #on your feet for extended periods of time
  manualLabor #involves manual labor
  overNight #involves working overnight
  collegeEd #requires a college degree
end

#definitions for our jobs
retail = job("Retail clerk", false, true, true, true, true, false, false)
construction = job("Construction", true, false, false, true, true, false, false)
nurse = job("Nurse", false, true, true, true, true, true, true)
programmer = job("Programmer", false, false, false, false, false, false, true)
engineer = job("Engineer", false, false, false, false, false, false, true)
law = job("Law Enforcement", true, false, true, true, false, true, false)

#our jobs array, as we ask the user questions, we can eliminate choices from this list and output the jobs at the end
jobs = job[]

#our blank user profile
user = job("null", false, true, true, true, true, true, true)


#calculates the jobs appropriate for the user (happens after the questionare)
function calcJobs(user)

  if user.worksOutside
    push!(jobs, construction)
    push!(jobs, law)
  end

  if user.fastPaced
    if !in(retail, jobs)
      push!(jobs, retail)
    end
  end

  if user.peoplePerson
    if !in(retail, jobs)
      push!(jobs,retail)
    elseif !in(law, jobs)
      push!(jobs, law)
    end
  end

  if user.feet
    if !in(retail, jobs)
      push!(jobs,retail)
    elseif !in(law, jobs)
      push!(jobs, law)
    end
  end

  if user.manualLabor
    if !in(retail, jobs)
      push!(jobs,retail)
    elseif !in(construction, jobs)
      push!(jobs, construction)
    elseif !in(law, jobs)
      push!(jobs, law)
    end
  end

  if user.overNight
    if !in(law, jobs) && user.manualLabor && user.feet && user.peoplePerson
      push!(jobs, law)
    end
  end

  if user.collegeEd
    if user.feet && user.overNight && user.peoplePerson && user.manualLabor
      push!(jobs, nurse)
    end
    push!(jobs, engineer)
    push!(jobs, programmer)
  end

   return jobs
end



calcJobs(user)
