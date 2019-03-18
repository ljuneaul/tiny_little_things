// function fire(bool) {

// 	if (bool) {

// 		var foo = 'bar';

// 		console.log(foo); // bar
// 	} else {

// 		console.log(foo); // undefined
// 	}
// }

// fire(false);

// class TaskCollection {

// 	constructor(tasks = []) {

// 		this.tasks = tasks;
// 	}

// 	prepare() {

// 		this.tasks.forEach(task => {

// 			console.log(this);
// 		});
// 	}
// }

// class Task {
// 	toGulp() {

// 		console.log('converting to gulp');
// 	}

// }

// new TaskCollection([
// 	new Task, new Task, new Task
// 	]).prepare();

// function applyDiscount(cost, discount = 0.5) {

// 	return cost - (cost * discount);
// }

// // Rest
// function sum(...numbers) {
// 	console.log(numbers);

// 	// return numbers.reduce(function(prev, current) {
// 	// 	console.log('prev: ' + prev);
// 	// 	console.log('current: ' + current);
// 	// 	return prev + current;
// 	// })

// 	return numbers.reduce((prev, current) => prev + current);
// }

// console.log(sum(1, 2, 3));

// // Spread
// function sum(x, y) {
// 	return x + y
// }

// let nums = [1, 2]

// console.log(sum(...nums));

// let name = 'Foo';
// let template = `
// 	<div class="Allert">
// 		<p>${name}</p>
// 	</div>
// 	`;

// console.log(template);

// function getPerson() {

// 	let name = 'John';
// 	let age = 25;

// 	return {name, 
// 		age,
// 		greet() {
// 			return `Hello, ${this.name}`;
// 		}
// 	};
// }

// alert(getPerson().greet());

// let data = {
// 	name: 'Karen',
// 	age: 32,
// 	results: ['foo', 'bar'],
// 	count: 30
// }
// let {results, count} = data;

// function getData({results, count}) {
// 	console.log(results, count);
// }
// getData({
// 	name: 'Karen',
// 	age: 32,
// 	results: ['foo', 'bar'],
// 	count: 30
// });

// class User {
// 	constructor(username, email) {
// 		this.username = username;
// 		this.email = email;
// 	}
// 	static register(...args) {
// 		return new User(...args);
// 	}
// 	get foo() {
// 		return 'foo';
// 	}
// 	changeEmail(newEmail) {
// 		this.email = newEmail;
// 	}
// }
// // let user = new User('Juneau', 'support@laracasts.com');
// let user = User.register('Juneau', 'support@laracasts.com');
// user.changeEmail('foo@example.com');
// console.dir(user);
// console.log(user.foo);

// startsWith(), endsWith(), includes(), and repeat()

// find, findIndex, and includes
// console.log(
// [2, 4, 6, 8, 10, 11, 13].find(function(item) {
// 	return item % 2 > 0
// })
// 	);

// function *numbers() {
// 	console.log('begin');
// 	yield 1;
// 	yield 2;
// 	yield 3;
// }
// let iterator = numbers();
// console.log(iterator.next());
// console.log(iterator.next());
// console.log(iterator.next());
// console.log(iterator.next());

function *range(start, end) {
	while (start < end) {
		yield start;
		start++;
	}
	yield start;
}
let irerator = range(1, 5);
// console.log(irerator.next());
// console.log(irerator.next());
// console.log(irerator.next());
for (let i of irerator) console.log(i);
console.log([...range(1, 5)]);











