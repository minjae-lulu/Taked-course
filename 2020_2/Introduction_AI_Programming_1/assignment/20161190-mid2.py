class FourCal:
    def __init__(self, first, second):
        self.first = first
        self.second = second

    def add(self):
        result = self.first + self.second
        return result


class morefourcal(FourCal):
    def pow(self):
        result = self.first ** self.second
        return result

class safefourcal(FourCal):
    def div(self):
        if self.second == 0:
            return 0
        else:
            return self.first / self.second


print(help(FourCal))
print(dir(FourCal))