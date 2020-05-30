threadCount=(1 2 4 8 12 24 48)
fileSize=(1 4 16 64)

for t in "${threadCount[@]}";
  do
    for f in "${fileSize[@]}"
        do
            echo "Threads: ${t} - File Size ${f}"
            echo "Threads: ${t} - File Size ${f}" >> mysort${f}GB.log
            (time java com.java.mergesort.Multithread ${f}gig ${t} >> t${t}f${f}) 2>> mysort${f}GB.log
            ./valsort t${t}f${f} 2>> mysort${f}GB.log
            rm t${t}f${f}

        done
  done
