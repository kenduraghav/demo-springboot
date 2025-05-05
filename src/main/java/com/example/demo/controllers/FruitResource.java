package com.example.demo.controllers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Fruit;

@RestController
@RequestMapping("/api/v1")
public class FruitResource {

	private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

	public FruitResource() {
		fruits.add(new Fruit("Apple", "Winter fruit"));
		fruits.add(new Fruit("Pineapple", "Tropical fruit"));
		fruits.add(new Fruit("Mango", "Tropical fruit"));
		fruits.add(new Fruit("Green Apple", "Winter fruit"));
	}

//	@ApiOperation(value = "View a list of fruits or returns the fruits list based on the description given in query parameter", produces = "application/json", response = List.class)
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/fruits", produces=MediaType.APPLICATION_JSON_VALUE)
	public Set<Fruit> list(@RequestParam(required=false) String desc) {
		return (desc == null) ? fruits : fruits.stream().filter(f-> f.getDescription().contains(desc)).collect(Collectors.toSet());
	}
	
	//@ApiOperation(value = "Returns a single fruit for the path parameter. Query param is case sensitive", response = Fruit.class)
	@GetMapping(value="/fruits/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Fruit findByName(@PathVariable String name) {
		return fruits.stream().filter(f -> f.getName().equals(name)).findAny().orElse(null);
	}
	

	//@ApiOperation(value = "Adds a fruit to the Set")
	@PostMapping("/fruits")
	public Set<Fruit> add(Fruit fruit) {
		fruits.add(fruit);
		return fruits;
	}

	//@ApiOperation(value = "Deletes a fruit from the Set")
	@DeleteMapping("/fruits")
	public Set<Fruit> delete(Fruit fruit) {
		fruits.remove(fruit);
		return fruits;
	}
}
