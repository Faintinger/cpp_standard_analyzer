The standard Follows the next format:

/*

	FileName.cpp

	Description of the CPP File

	Name Last

	31/12/2016 (Date)

	version 1.0

*/

Includes
...

/*

	FunctionName

	Description of the function


	Parameters: // in order

	param1 Description 
	param2 Description
	.
	.

	Returns:

	description of return
*/

type functionName(param1, param2, ...){
	return 0;
}

	...

/*

	Main Function

	Description of the Main



	Parameters:

	none

	Returns:

	Description of return

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

	cout << "Force = " << functionName(dMass, dAcceleration) << endl;
	
	return 0;

}
