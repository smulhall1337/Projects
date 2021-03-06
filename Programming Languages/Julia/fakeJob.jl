#this package creates fake jobs
# we assign these fake jobs based on a questionare the user would complete
#ultimately its useless since the jobs we're random everytime the function was called

Pkg.add("Faker")

using Faker

function fakeJob()
runningTotal = 0
print("\nEnter your name: ")
name = chomp(readline(STDIN))
println("\nHello $(name)!")
println("Time to find you a job!")
println("This program will choose the best job for you based on 5 simple questions!\n")

questionList = String["how much do you like working outside", 
		      "how much do you like computers",
                      "how much do you like velociraptors",
                      "how useful do you think that last question will be",
                      "how ready are you to start working"]

for i=1:5
 	print("Scale of 1-5, $(questionList[i]): ")
	response = parse(Int64,readline(STDIN))

	if response > 5 || response < 0
		println("Yeah ok, 1-5 buddy, I'll just make that a 3.")
                runningTotal += 3
	else
        	runningTotal += response	
	end
end

println("\nOn a scale of 5-25 you're a $(runningTotal)!")
println("That means the best job for you is: $(Faker.job())!")
println("\nAll job assignments are final. Goodbye.")

end
