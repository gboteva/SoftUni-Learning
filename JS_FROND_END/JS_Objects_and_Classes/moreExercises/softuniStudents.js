function softuniStudents(array) {
  class Student {
    constructor(username, email, credits) {
      this.username = username;
      this.email = email;
      this.credits = credits;
    }
  }

  let courses = {};

  let studentsByCourses = {};

  for (let input of array) {
    if (input.includes(":")) {
      let course = input.split(": ")[0];
      let capacity = Number(input.split(": ")[1]);
      if (!courses.hasOwnProperty(course)) {
        courses[course] = capacity;
      } else {
        courses[course] += capacity;
      }
    } else {
      let tokens = input.split(" ");
      let username = tokens[0].split("[")[0];
      let credits = Number(
        tokens[0].split("[")[1].substring(0, tokens[0].split("[")[1].length - 1)
      );
      let email = tokens[3];
      let courseName = tokens[5];
      let student = new Student(username, email, credits);

      if (courses[courseName] !== undefined &&
        !studentsByCourses.hasOwnProperty(courseName)) {
        studentsByCourses[courseName] = [];
      }
      if (courses[courseName] !== undefined) {
        studentsByCourses[courseName].push(student);
      }
    }
  }

  for (let key in studentsByCourses) {
    let courseName = key;
    let students = studentsByCourses[key];
    let capacity = courses[courseName];

    if (capacity < students.length) {
      let difference = students.length - capacity;
      studentsByCourses[key].splice(capacity, difference);
    }
  }

  let sortedCoursesByCountOfStudents = Object.entries(studentsByCourses).sort(
    (a, b) => {
      return b[1].length - a[1].length;
    }
  );
    console.log(sortedCoursesByCountOfStudents);

  for (let course of sortedCoursesByCountOfStudents) {
    let courseName = course[0];
    let students = course[1];
    let sortedStudentByCredits = students.sort((a, b) => {
      return b.credits - a.credits;
    });
    let capacity = courses[courseName];
    let diff = capacity - students.length;
    console.log(`${courseName}: ${diff} places left`);

    for (let st of sortedStudentByCredits) {
      console.log(`--- ${st.credits}: ${st.username}, ${st.email}`);
    }
  }
}

softuniStudents([
  "JavaBasics: 2",
  "user1[25] with email user1@user.com joins C#Basics",
  "C#Advanced: 3",
  "JSCore: 4",
  "user2[30] with email user2@user.com joins C#Basics",
  "user13[50] with email user13@user.com joins JSCore",
  "user1[25] with email user1@user.com joins JSCore",
  "user8[18] with email user8@user.com joins C#Advanced",
  "user6[85] with email user6@user.com joins JSCore",
  "JSCore: 2",
  "user11[3] with email user11@user.com joins JavaBasics",
  "user45[105] with email user45@user.com joins JSCore",
  "user007[20] with email user007@user.com joins JSCore",
  "user700[29] with email user700@user.com joins JSCore",
  "user900[88] with email user900@user.com joins JSCore",
]);
