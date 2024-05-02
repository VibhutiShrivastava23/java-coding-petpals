package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.dto.EventDto;
import com.exception.InvalidInputException;
import com.exception.InvalidPetAgeException;
import com.model.AdoptionEvent;
import com.model.Donation;
import com.model.Pets;
import com.service.AdoptionService;
import com.service.DonationService;
import com.service.EventService;
import com.service.PetPalsService;

public class PetPalsController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventService eventService = new EventService();
		AdoptionService adoptionService = new AdoptionService();
		DonationService donationService = new DonationService();
		PetPalsService petService = new PetPalsService();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n-------------Welcome to Pet Pals-------------");
			System.out.println("Press 1. Add Pet");
			System.out.println("Press 2. Remove Pet by Soft Delete");
			System.out.println("Press 3. View All Available Pets");
			System.out.println("Press 4. Make Donation");
			System.out.println("Press 5. View All Donations");
			System.out.println("Press 6. View All Adoption Events");
			System.out.println("Press 7. Host Event");
			System.out.println("Press 8. View Events and Participants");
			System.out.println("Press 0. To Exit");

			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting from PetPals");
				break;
			}

			switch (input) {
//1
			case 1:
				Random random = new Random();
				int randomNumber = random.nextInt();
				int id = randomNumber < 0 ? randomNumber * -1 : randomNumber;

				System.out.println("Enter Name");
				sc.nextLine();
				String name = sc.nextLine();

				System.out.println("Enter Age");
				int age = sc.nextInt();

				System.out.println("Enter breed");
				String breed = sc.next();

				System.out.println("Enter Available to AdoptionService(yes/ no) ");
				String available = sc.next();

				Pets pet = new Pets(id, name, age, breed, available);

				try {
					int status = petService.save(pet);
					if (status == 1)
						System.out.println("New Pet is Added Successfully");
					else
						System.out.println("Insert failed");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (InvalidPetAgeException e) {
					System.out.println(e.getMessage());
				}
				break;
//2			
			case 2:
				try {
					List<Pets> list = petService.findAll();
					for (Pets p : list) {
						System.out.println(p);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				System.out.println("Enter Pet ID");
				int petid = sc.nextInt();

				try {
					petService.softDeleteById(petid);
					System.out.println("Pet is Removed ");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}

				break;
//3			
			case 3:
				try {
					List<Pets> list = petService.findAllAvailable();
					for (Pets p : list) {
						System.out.println(p);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
//4
			case 4:
				Random randomid = new Random();
				int randomNumberid = randomid.nextInt();
				int doid = randomNumberid < 0 ? randomNumberid * -1 : randomNumberid;

				System.out.println("Enter Donor Name");
				sc.nextLine();
				String donor = sc.nextLine();

				System.out.println("Enter amount");
				double amount = sc.nextDouble();

				try {
					List<Pets> list = petService.findAllAvailable();
					for (Pets p : list) {
						System.out.println(p);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				System.out.println("Enter pet Id");
				int petId = sc.nextInt();

				Donation donation = new Donation(doid, donor, amount, petId);

				try {
					int status = donationService.makeDonation(donation);
					if (status == 1)
						System.out.println("Donation Done Successfully");
					else
						System.out.println("Insert failed");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
//5
			case 5:
				try {
					List<Donation> list = donationService.findAllDonation();
					for (Donation d : list) {
						System.out.println(d);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
//6
			case 6:
				try {
					List<AdoptionEvent> list = adoptionService.findAllEvent();
					for (AdoptionEvent d : list) {
						System.out.println(d);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

//7
			case 7:
				Random randomEid = new Random();
				int randomNumberEid = randomEid.nextInt();
				int Eid = randomNumberEid < 0 ? randomNumberEid * -1 : randomNumberEid;

				System.out.println("Enter Name of Event");
				sc.nextLine();
				String eName = sc.nextLine();

				System.out.println("Enter Event Date");
				String date = sc.next();

				System.out.println("Enter Location of The Event");
				String address = sc.next();

				AdoptionEvent event = new AdoptionEvent(Eid, eName, date, address);

				try {
					int status = eventService.hostEvent(event);
					if (status == 1)
						System.out.println("New Event Added");
					else
						System.out.println("Insert failed");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
//8			
			case 8:
				try {
					List<EventDto> list = adoptionService.getParticipation();

					for (EventDto e : list) {
						System.out.println(e);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			}

		}
		sc.close();
	}
}
