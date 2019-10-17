/*

	Application_Correct_100.cpp



	This application calculates the force of a moving object

	using its values for mass and acceleration, provided

	by the user, accordingly to the Newton’s Second Law.



	Juan Perez

	31/12/2016

	version 1.0

*/

#include <iostream>

using namespace std;

/*

	CalculateForce



	To calculate the value of the force from mass and

	acceleration values, using the formula from the Newton’s

	Second Law.



	Parameters:

	dMass mass of the moving object

	dAcceleration acceleration of the moving object

	Returns:

	return the operation = mass * acceleration

*/

double calculateForce(double dMass, double dAcceleration)
{
	// getting the force value

	double dForce = dMass * dAcceleration;

	// returnng the result

	return dForce;

}

/*

	Main Function



	Prompt values for mass and acceleration to calculate the

	force for a moving object, using the function caclulateForce

	and display the result.



	Parameters:

	none

	Returns:

	an integer value with the error number (0 = none)

*/

int main()

{

	// defining variable to store the mass of the object

	double dMass;

	// defining variable to store the acceleration of the object

	double dAcceleration;

	// Get values for mass and acceleration

	cout << "Enter mass value : " << endl;

	cin >> dMass;

	cout << "Enter acceleration value : " << endl;

	cin >> dAcceleration;



	// displaying the result of the force

	cout << "Force = " << calculateForce(dMass, dAcceleration) << endl;

	return 0;

}
