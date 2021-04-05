# Raspberry Pi 4B Wifi Configuration

This is a tutorial to connect your RPi 4 to SUTD Wifi (PEAP-Enterprise) or SUTD school ethernet (IEEE 802.1X) compiled by Kevinskwk

*reference: https://github.com/randName/SUTD-data*

*https://wiki.archlinux.org/index.php/WPA_supplicant* 

- Open terminal, and edit `/etc/network/interfaces` by command 
    `$ sudo nano /etc/network/interfaces`,
    add the following lines to the end of the file:
    
    ```
    auto eth0
    allow-hotplug eth0
    iface eth0 inet manual
        wpa-driver wired
    wpa-conf /etc/wpa_supplicant/wpa_wired.conf
    
    auto wlan0
    allow-hotplug wlan0
    iface wlan0 inet manual
        wpa-conf /etc/wpa_supplicant/wpa_supplicant.conf
    ```
    
- Then, edit `/etc/wpa_supplicant/wpa_supplicant.conf` by command
    `$ sudo nano /etc/wpa_supplicant/wpa_supplicant.conf`,
    add the following lines to the end of the file:
    ```
    network={
        ssid="SUTD_Wifi"
        scan_ssid=1
        proto=WPA2
        key_mgmt=WPA-EAP
        eap=PEAP
        phase1="peaplabel=0"
        phase2="auth=MSCHAPV2"
        identity="[Student ID]"
        password="[password]"
    }

    network={
        ssid="eduroam"
        scan_ssid=1
        proto=WPA2
        key_mgmt=WPA-EAP
        eap=PEAP
        phase1="peaplabel=0"
        phase2="auth=MSCHAPV2"
        identity="[Student ID]@sutd.edu.sg"
        password="[password]"
        priority=0
    }
    ```
    
- Finally, create a file named `wpa_wired.conf` in `/etc/wpa_supplicant/` directory by the command `$ sudo nano /etc/wpa_supplicant/wpa_wired.conf`
    then add the following lines into the file:
    ```
    ap_scan=0
    eapol_version=2

    network={
            key_mgmt=IEEE8021X
            eap=PEAP
            eapol_flags=0
            phase1="peaplabel=0"
            phase2="auth=MSCHAPV2"
            identity="[Student ID]"
            password="[password]"
    }
    ```
    
- Remember to replace all the `[Student ID]` with your student ID and `[password]` with your password. Later when you change your password or change the credential to log in, you need to change these files manually.

- After editing these files, kill the wpa_supplicant process first with command:
    ```
    $ sudo killall wpa_supplicant
    ```
    and then restart wpa_supplicant with wext driver with the command:
   ```
    $ sudo wpa_supplicant -B -i wlan0 -D wext -c /etc/wpa_supplicant/wpa_supplicant.conf
   ```
   where:
   
   - `-B` - Fork into background.
   - `-c *filename*` - Path to configuration file.
   - `-i *interface*` - Interface to listen on. If using ethernet, use `-i eth0` instead of `-i wlan0`.
   - `-D driver` - Optionally specify the driver to be used. For a list of supported drivers see the output of `wpa_supplicant -h`.
     - `nl80211` is the current standard, but not all wireless chip's modules support it.
     - `wext` is currently deprecated, but still widely supported.
   
- In our case, `nl80211` is not supported by RPi 4, so we need to manually specify to use `wext` driver.

- Then wait for the wifi to get connected, you are ready to go!

## Configure Raspberry Pi to automatically connect to SUTD_Wifi on boot

* Create `wifi.sh` script file using command `nano /home/pi/wifi.sh` and pasting the following

  ```
  #!usr/bin/env bash
  
  sudo killall wpa_supplicant
  sleep 3
  sudo wpa_supplicant -B -i wlan0 -D wext -c /etc/wpa_supplicant/wpa_supplicant.conf
  ```
  
  

* Create autostart folder using command `mkdir /home/pi/.config/autostart`

* Create `.desktop` file that runs on boot using command `nano /home/pi/.config/autostart/wifi.desktop` and pasting the following
  ```
  [Desktop Entry]
  Encoding=UTF-8
  Version=1.0
  Type=Application
  Terminal=false
  Exec=/home/pi/wifi.sh
  Name=SUTD Wifi
  ```
	