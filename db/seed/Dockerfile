FROM python:3.11-slim
COPY requirements.txt .
RUN pip3 install -r requirements.txt --no-cache-dir
COPY . /app
CMD ["python3", "/app/seed_db.py"] 