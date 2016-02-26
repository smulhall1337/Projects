#tested the functionality of the Winston package in julia
#basically just plots a graph with given input

Pkg.add("Winston")

Pkg.add("ImplicitEquations")

using Winston

using ImplicitEquations

a,b = -1,2
f(x,y) = y^4 - x^4 + a*y^2 + b*x^2
plot(f == 0)
