#! /bin/csh -f

#lowercasify
#
#Changes all files in the directory to the same name
#except lower case


@ total = 0
@ unchanged = 0
@ alreadyexist = 0
@ renamed = 0



if ( $#argv == 0 ) then
    set thedir = .
else
    set thedir = $argv[1]
endif

if (! -d $thedir ) then
    echo "$thedir is not a directory, exiting"
    exit
endif

if (! -r $thedir ) then
    echo "cannot read $thedir, exiting"
    exit
endif

cd $thedir

foreach file ( * )
    @ total++

    set lower = `echo $file | tr '[A-Z]' '[a-z]'`

    if (  $lower == $file ) then
	@ unchanged ++
	echo "$file unchanged"
    
    else if ( -f $lower ) then
	@ alreadyexist ++
	echo "$file -> $lower : $lower already exists in $thedir!"

    else
	mv -i $file $lower
	@ renamed ++
	echo "$file renamed to $lower"
    endif
end

echo "Directory      :  $thedir"
echo "Total Files    :  $total"
echo "renamed files  :  $renamed"
echo "existing files :  $alreadyexist"
echo "unchanged files:  $unchanged"
