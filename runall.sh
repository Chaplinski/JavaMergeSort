threadCount=(1 2 4 8 12 24 48)
fileSize=(1 4 16 64)

for t in "${threadCount[@]}";
  do
    for f in "${fileSize[@]}"
        do
            echo "Threads: ${t} - File Size ${f}"
            echo "Threads: ${t} - File Size ${f}" >> linsort${f}GB.log
            (time sort --parallel=${t} --buffer-size=8G ${f}gig >> t${t}f${f}) 2>> linsort${f}GB.log
            ./valsort t${t}f${f} 2>> linsort${f}GB.log
            rm t${t}f${f}

        done
  done
