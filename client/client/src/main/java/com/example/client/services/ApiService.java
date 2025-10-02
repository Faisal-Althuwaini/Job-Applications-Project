package com.example.client.services;

import com.example.client.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ApiService {
    private static ApiService instance;
    private static final String BASE_URL = "http://localhost:8080";

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private String jwtToken;

    private ApiService() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public AuthResponse login(String email, String password) throws IOException {
        LoginRequest loginRequest = new LoginRequest(email, password);
        String json = objectMapper.writeValueAsString(loginRequest);

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(BASE_URL + "/auth/login")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Login failed: " + response.message());
            }

            String responseBody = response.body().string();
            AuthResponse authResponse = objectMapper.readValue(responseBody, AuthResponse.class);
            this.jwtToken = authResponse.getToken();
            return authResponse;
        }
    }

    public void logout() {
        this.jwtToken = null;
    }

    public List<Job> getAllJobs() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/jobs")
                .addHeader("Authorization", "Bearer " + jwtToken)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed: " + response.message());
            }

            String responseBody = response.body().string();
            Job[] jobsArray = objectMapper.readValue(responseBody, Job[].class);
            return Arrays.asList(jobsArray);
        }
    }

    public Job createJob(String title, String description, String location, String department) throws IOException {
        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setLocation(location);
        job.setDepartment(department);

        String json = objectMapper.writeValueAsString(job);

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(BASE_URL + "/jobs")
                .addHeader("Authorization", "Bearer " + jwtToken)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed: " + response.message());
            }

            String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, Job.class);
        }
    }

    public void deleteJob(Long jobId) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/jobs/" + jobId)
                .addHeader("Authorization", "Bearer " + jwtToken)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed: " + response.message());
            }
        }
    }

    public List<Application> getAllApplications() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/applications")
                .addHeader("Authorization", "Bearer " + jwtToken)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed: " + response.message());
            }

            String responseBody = response.body().string();
            Application[] applicationsArray = objectMapper.readValue(responseBody, Application[].class);
            return Arrays.asList(applicationsArray);
        }
    }

    public void deleteApplication(Long applicationId) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/applications/" + applicationId)
                .addHeader("Authorization", "Bearer " + jwtToken)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed: " + response.message());
            }
        }
    }
}