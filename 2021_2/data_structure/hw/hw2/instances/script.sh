# solves and checks all instances
for i in {1..8}
        do
                echo "solving instance $i"
                ../code/solve instance"$i".in > instance"$i".sol
                echo "checking instance $i"
                ../code/check instance"$i".in instance"$i".sol
                echo "----------------"
        done
for i in {1..4}
        do
                echo "solving bad instance $i"
                ../code/solve badinstance"$i".in > badinstance"$i".sol
        done

