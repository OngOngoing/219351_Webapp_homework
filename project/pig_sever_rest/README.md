# Pig Server
#Django server & Pig
github : https://github.com/jarunithi/WebAppStupidServer
## Setup

1. Setup virtual environment: `virtualenv venv; source venv/bin/activate`
2. Setup requirements: `pip install -r requirements.txt`
3. Sync database: `python manage.py migrate`
4. Add user: `python manage.py createsuperuser`

## Development

- Run webserver: `python manage.py runserver 0.0.0.0:80`

#Calling pig from django
- Use subprocess in python to run pig.(bash/sh)
- Use subprocess in python to merge result into .txt file.
- Collect input and result in django model/database.

#Bug & Issue
- "No space left on the disk". Happen many time while running pig. (7.4GB used from 10GB)

