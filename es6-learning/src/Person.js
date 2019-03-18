class Person {

	constructer(name) {
		this.name = name;
	}

	greet() {

		return this.name + ' says hello.';
	}
}

console.log(new Person('Juneau').greet())