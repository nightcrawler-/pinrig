# PIN Rig

[![](https://jitpack.io/v/nightcrawler-/pinrig.svg)](https://jitpack.io/#nightcrawler-/pinrig)

PIN Rig is a custom anddroid widget that simplifies the input and interraction with PIN number. Currently only four-digit PINs are supported.

### Installation

Add to your project level build.gradle file:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Then to your module level build.gradle:
```
dependencies {
	        implementation 'com.github.nightcrawler-:pinrig:<version>'
	        implementation 'com.github.nightcrawler-:clamnumpad:<version>'
	}
```

The version information can be found from the releases tab.

### Usage

Just set the KeyPad to the PIN Rig, and implement the pinrig listener, then call the setup method

```     
           binding.pinRig.keypad = binding.keypad
           binding.pinRig.listener = this
           binding.pinRig.setup()
   
```

### Planned Features!

  - XML Attributes for rig shape and color
 
### In Action
![Screenshot](images/1.png)

