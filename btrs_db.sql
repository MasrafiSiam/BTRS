-- ============================================================
--  BTRS — Bus Ticket Reservation System
--  Database Schema  (MySQL 8+)
-- ============================================================

CREATE DATABASE IF NOT EXISTS btrs_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE btrs_db;

-- ── USERS ────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS users (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100)        NOT NULL,
    email      VARCHAR(150)        NOT NULL UNIQUE,
    password   VARCHAR(255)        NOT NULL,
    created_at TIMESTAMP           DEFAULT CURRENT_TIMESTAMP
);

-- ── BUSES ────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS buses (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    bus_name     VARCHAR(100)  NOT NULL,
    route_from   VARCHAR(100)  NOT NULL,
    route_to     VARCHAR(100)  NOT NULL,
    travel_date  DATE          NOT NULL,
    travel_time  TIME          NOT NULL,
    total_seats  INT           NOT NULL DEFAULT 40,
    created_at   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);

-- ── BOOKINGS ─────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS bookings (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT  NOT NULL,
    bus_id       INT  NOT NULL,
    seat_number  INT  NOT NULL,
    booked_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)  ON DELETE CASCADE,
    FOREIGN KEY (bus_id)  REFERENCES buses(id)  ON DELETE CASCADE,
    UNIQUE KEY uq_seat (bus_id, seat_number)
);

-- ── SAMPLE DATA ──────────────────────────────────────────────
INSERT INTO buses (bus_name, route_from, route_to, travel_date, travel_time, total_seats) VALUES
('Green Line Express',   'Dhaka',      'Chittagong', '2026-04-01', '07:00:00', 40),
('Shyamoli Paribahan',   'Dhaka',      'Chittagong', '2026-04-01', '10:30:00', 40),
('Hanif Enterprise',     'Dhaka',      'Chittagong', '2026-04-01', '22:00:00', 40),
('Eagle Travels',        'Dhaka',      'Sylhet',     '2026-04-01', '08:00:00', 40),
('National Travels',     'Dhaka',      'Sylhet',     '2026-04-01', '14:00:00', 40),
('BRTC Deluxe',          'Dhaka',      'Khulna',     '2026-04-02', '06:30:00', 40),
('Soudia Enterprise',    'Dhaka',      'Rajshahi',   '2026-04-02', '09:00:00', 40),
('GreenLine Deluxe',     'Chittagong', 'Dhaka',      '2026-04-02', '07:00:00', 40),
('Shyamoli AC',          'Sylhet',     'Dhaka',      '2026-04-02', '08:00:00', 40),
('Royal Coach',          'Dhaka',      'Cox Bazar',  '2026-04-03', '05:30:00', 40),
('Desh Travels',         'Dhaka',      'Barisal',    '2026-04-03', '09:00:00', 40),
('Ena Transport',        'Rajshahi',   'Dhaka',      '2026-04-03', '07:00:00', 40);
