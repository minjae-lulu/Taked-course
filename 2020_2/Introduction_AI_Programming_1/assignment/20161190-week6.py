class Student:
    def __init__(self, id, name):
        self.id = id
        self.name = name
        self.score = 0
    def getId(self):
        return self.id
    def getName(self):
        return self.name
    def setScore(self, score):
        self.score = score
    def getScore(self):
        return self.score

class CalcScore:
    def __init__(self):
        self.students = []
    def addStudent(self, student):
        self.students.append(student)

    def avgScore(self):
        sum = 0
        for student in self.students:
            sum += student
        average = sum / len(self.students)
        return average

calc = CalcScore()
num = int(input("How many students: "))
for i in range(num):
    student_id = input("Enter the student ID: ")
    student_name = input("Enter the student name: ")
    student_score = float(input("Enter the student score: "))
    obj = Student(student_id, student_name)

    obj.setScore(student_score)
    calc.addStudent(obj.getScore())

print("Average Score =", calc.avgScore())
